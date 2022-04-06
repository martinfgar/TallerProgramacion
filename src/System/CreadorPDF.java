package System;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map.Entry;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import Modelos.Pieza;
import Modelos.Factura;
import Modelos.Reparacion;



public class CreadorPDF {
	
	
	
	
    public static void crearPdfFactura(Factura factura) throws FileNotFoundException {
    	String dest = "facturas/Factura "+factura.getId()+".pdf";
    	File file = new File(dest);
		file.getParentFile().mkdirs();
    	//Inicializar escritor pdf
    	FileOutputStream fos = new FileOutputStream(dest);
    	PdfWriter writer = new PdfWriter(fos);
    	
    	//Inicializar documento pdf
    	PdfDocument pdf = new PdfDocument(writer);
    	
    	//Inicializar documento
    	Document documento = new Document(pdf,PageSize.A4);
    	
    	//Escribir en el documento
    	
    	documento.add(new Paragraph("Cliente : " +factura.getCoche().getDueno().toString()));
    	documento.add(new Paragraph("Vehiculo: "+ factura.getCoche().toString()));
    	documento.add(new Paragraph("Fecha entrada: "+ factura.getFecha_entrada()));
    	documento.add(new Paragraph("Fecha Fin: "+ factura.getFecha_fin()));
    	documento.add(new Paragraph("Operaciones:"));
    	
    	for (Reparacion rep : factura.getReparaciones()) {
    		documento.add(new Paragraph("Descripcion de operacion: "+ rep.getComentarios()));
    		Table piezas = new Table(4);
    		piezas.addHeaderCell("Marca");
        	piezas.addHeaderCell("Modelo");
        	piezas.addHeaderCell("Precio Ud");
        	piezas.addHeaderCell("Cantidad");
        	try {
        		for (Entry<Pieza,Integer> linea : rep.getPiezas().entrySet()) {
        			piezas.startNewRow();
        			piezas.addCell(linea.getKey().getMarca());
        			piezas.addCell(linea.getKey().getModelo());
        			piezas.addCell(Double.toString(linea.getKey().getPrecio()));
        			piezas.addCell(Integer.toString(linea.getValue()));
        		}
        		documento.add(piezas);
        	} catch(Exception e) {
        		//En caso de una operacion sin piezas
        	}
    		
    	} 
    	
    	documento.add(new Paragraph("Total piezas y mano de obra: "+factura.getPrecio_total()+"\u20ac"));
    	
    	documento.close();
    }
}