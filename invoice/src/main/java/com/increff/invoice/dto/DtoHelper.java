package com.increff.invoice.dto;

import com.increff.invoice.model.InvoiceForm;
import com.increff.invoice.model.OrderItemData;
import org.apache.fop.apps.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DtoHelper {
    public static void createPDF() throws Exception{
        File xsltFile = new File("template.xsl");
        StreamSource xmlSource = new StreamSource(new File("invoiceDataXML.xml"));
        FopFactory fopFactory = FopFactory.newInstance();
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        OutputStream out;
        out = new java.io.FileOutputStream("invoice.pdf");
        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }

    public static void createResponse(HttpServletResponse response, byte[] encodedBytes) throws IOException {
        String pdfFileName = "invoice.pdf";
        response.reset();
        response.addHeader("Pragma", "public");
        response.addHeader("Cache-Control", "max-age=0");
        response.setHeader("Content-disposition", "attachment;filename=" + pdfFileName);
        response.setContentType("application/pdf");
        response.setContentLength(encodedBytes.length);
        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(encodedBytes);
        servletOutputStream.flush();
        servletOutputStream.close();
    }

    public static void createXml(InvoiceForm orderDetailsData) throws Exception {
        String xmlFilePath = "invoiceDataXML.xml";
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root = document.createElement("invoice");
        document.appendChild(root);
        Element date = document.createElement("date");
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date dateobj = new Date();
        String date2 = df.format(dateobj);
        date.appendChild(document.createTextNode(date2));
        root.appendChild(date);
        Element time = document.createElement("time");
        DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
        Date dateobj2 = new Date();
        String time2 = df.format(dateobj2);
        time.appendChild(document.createTextNode(time2));
        root.appendChild(time);
        List<OrderItemData> orderDetailsDataItems = orderDetailsData.getOrderItemList();
        List<String> productName = orderDetailsData.getProductName();
        int i = 0;
        double totalAmount = 0;
        for (i = 0; i < orderDetailsDataItems.size(); i++) {
            double total = 0;
            Element item = document.createElement("item");
            Element id = document.createElement("id");
            Element proName = document.createElement("name");
            Element quantity = document.createElement("quantity");
            Element sellingPrice = document.createElement("sellingPrice");
            root.appendChild(item);
            id.appendChild(document.createTextNode(String.valueOf(i + 1)));
            item.appendChild(id);

            proName.appendChild(document.createTextNode(String.valueOf(productName.get(i))));
            item.appendChild(proName);
            totalAmount = totalAmount + orderDetailsDataItems.get(i).getQuantity() * orderDetailsDataItems.get(i).getSellingPrice();
            total = total + orderDetailsDataItems.get(i).getQuantity() * orderDetailsDataItems.get(i).getSellingPrice();

            quantity.appendChild(document.createTextNode(String.valueOf(orderDetailsDataItems.get(i).getQuantity())));
            item.appendChild(quantity);

            sellingPrice.appendChild(document.createTextNode(String.format("%.2f", orderDetailsDataItems.get(i).getSellingPrice())));
            item.appendChild(sellingPrice);
            Element cost = document.createElement("cost");
            cost.appendChild(document.createTextNode(String.format("%.2f", total)));
            item.appendChild(cost);

        }
        Element total = document.createElement("total");
        total.appendChild(document.createTextNode("Rs. " + String.format("%.2f", totalAmount)));
        root.appendChild(total);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(xmlFilePath));
        transformer.transform(domSource, streamResult);
    }

}