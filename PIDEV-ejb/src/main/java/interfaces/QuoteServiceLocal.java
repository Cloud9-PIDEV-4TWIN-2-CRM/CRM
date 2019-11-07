package interfaces;

import java.io.FileNotFoundException;

import javax.ejb.Local;

import com.itextpdf.text.DocumentException;

import Entities.Quote;

@Local
public interface QuoteServiceLocal {
	public Quote detailsDevis(int idProspect);

	public String pdfCreator(int idProspect) throws FileNotFoundException, DocumentException;
}
