package resources;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.StreamingOutput;

import org.apache.commons.io.IOUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

import interfaces.QuoteServiceLocal;
import services.QuoteService;

@Path("quote")
public class QuoteResource {

	@EJB
	QuoteServiceLocal qsl;

	@GET
	@Path("{idProspect}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response test(@PathParam("idProspect") int idP) throws FileNotFoundException, DocumentException {
		qsl.pdfCreator(idP);
		String filePath = "C:\\Users\\salme\\OneDrive\\Bureau\\devis" + idP + ".pdf";
		File file = new File(filePath);
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=" + idP + ".pdf");
		return response.build();
	}
}
