package services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Entities.Basket;
import Entities.Offer;
import Entities.Product;
import Entities.ProductQuantity;
import Entities.Quote;
import interfaces.QuoteServiceLocal;

/**
 * Session Bean implementation class QuoteService
 */
@Stateless

public class QuoteService implements QuoteServiceLocal {

	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public QuoteService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Quote detailsDevis(int idProspect) {
		Quote quote = new Quote();
		int idBasket = em.createQuery("select u.id from Basket u where u.prospect=" + idProspect, Integer.class)
				.getSingleResult();
		List<Integer> productQte = new ArrayList<Integer>();
		List<String> productName = new ArrayList<String>();
		List<Float> productPrice = new ArrayList<Float>();
		List<Float> productReduction = new ArrayList<Float>();
		Offer o = null;
		List<ProductQuantity> productIds = em
				.createQuery("select u from ProductQuantity u where u.basket=" + idBasket, ProductQuantity.class)
				.getResultList();
		for (int i = 0; i < productIds.size(); i++) {
			int qte = em.createQuery(
					"select u.qte from ProductQuantity u where u.product=" + productIds.get(i).getProduct().getId(),
					Integer.class).getSingleResult();
			productQte.add(qte);
			Product p = em.createQuery("select u from Product u where u.id=" + productIds.get(i).getProduct().getId(),
					Product.class).getSingleResult();
			productName.add(p.getName());
			productPrice.add(p.getPrice());
			try {
				o = em.createQuery("select u from Offer u where u.product=" + productIds.get(i).getProduct().getId(),
						Offer.class).getSingleResult();

			} catch (NoResultException e) {

			}
			if (o == null) {
				productReduction.add(0f);
			} else if (o != null) {
				productReduction.add(o.getReduction());
			}

		}
		quote.setNameProduct(productName);
		quote.setPrixProduct(productPrice);
		quote.setQuantityProduct(productQte);
		quote.setReductionProduct(productReduction);
		return quote;
	}

	@Override
	public String pdfCreator(int idProspect) throws FileNotFoundException, DocumentException {
		Quote o = detailsDevis(idProspect);
		Document document = new Document();
		PdfWriter.getInstance(document,
				new FileOutputStream("C:\\Users\\salme\\OneDrive\\Bureau\\devis" + idProspect + ".pdf"));

		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 24, BaseColor.RED);
		Paragraph chunk = new Paragraph("Quote", font);
		// chunk.setFont(font);
		Paragraph space = new Paragraph("        ");
		chunk.setAlignment(Element.ALIGN_CENTER);
		document.add(chunk);
		document.add(space);
		PdfPTable table = new PdfPTable(4);
		addTableHeader(table);
		List<String> listname = o.getNameProduct();
		List<Integer> listqte = o.getQuantityProduct();
		List<Float> listprice = o.getPrixProduct();
		List<Float> listreduction = o.getReductionProduct();
		float p = 0.0f;
		;
		for (int i = 0; i < listname.size(); i++) {
			table.addCell(listname.get(i));
			table.addCell("" + listqte.get(i));
			table.addCell("" + listprice.get(i));
			table.addCell("" + listreduction.get(i));
			if (listreduction.get(i) == 0.0f)
				p = p + (listprice.get(i) * listqte.get(i));
			else {
				float r = ((listprice.get(i) * listreduction.get(i)) / 100.0f);
				p = p + ((listprice.get(i) - r) * listqte.get(i));
			}

		}
		Paragraph total = new Paragraph("the total price: " + p + " DT", font);
		// total.setFont(font);
		total.setAlignment(Element.ALIGN_CENTER);
		document.add(table);
		document.add(total);
		document.close();
		return "ok";

	}

	private void addRows(PdfPTable table) {
		table.addCell("row 1, col 1");
		table.addCell("row 1, col 2");
		table.addCell("row 1, col 3");
	}

	private void addTableHeader(PdfPTable table) {
		Stream.of("PRODUCT NAME", "PRODUCT QUANTITY", "PRODUCT PRICE", "REDUCTION").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

}
