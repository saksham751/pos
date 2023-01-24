package com.increff.invoice.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.increff.invoice.model.InvoiceForm;
import com.increff.invoice.model.OrderItemData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GenerateXML {

    public static void createXml(InvoiceForm orderDetailsData)
            throws ParserConfigurationException, TransformerException {

            String xmlFilePath = "billDataXML.xml";

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            int i = 0;
            // root element
            Element root = document.createElement("bill");
            document.appendChild(root);
            double finalBill = 0;

            Element date = document.createElement("date");
            date.appendChild(document.createTextNode(getDate()));
            root.appendChild(date);

            Element time = document.createElement("time");
            time.appendChild(document.createTextNode(getTime()));
            root.appendChild(time);
            // Create elements from OrderDetailsData list

            List<OrderItemData> orderDetailsDataItems = orderDetailsData.getOrderItemList();
            for (i = 0; i < orderDetailsDataItems.size(); i++) {
                Element item = document.createElement("item");
                root.appendChild(item);
                Element id = document.createElement("id");
                id.appendChild(document.createTextNode(String.valueOf(i + 1)));
                item.appendChild(id);

                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(String.valueOf(orderDetailsDataItems.get(i).getProductId())));
                item.appendChild(name);
                // Calculate total bill amount
                finalBill = finalBill + orderDetailsDataItems.get(i).getQuantity() * orderDetailsDataItems.get(i).getSellingPrice();
                double totalCost = 0;
                totalCost = totalCost + orderDetailsDataItems.get(i).getQuantity() * orderDetailsDataItems.get(i).getSellingPrice();
                Element quantity = document.createElement("quantity");
                quantity.appendChild(document.createTextNode(String.valueOf(orderDetailsDataItems.get(i).getQuantity())));
                item.appendChild(quantity);

                Element sellingPrice = document.createElement("sellingPrice");
                sellingPrice.appendChild(document.createTextNode(String.format("%.2f", orderDetailsDataItems.get(i).getSellingPrice())));
                item.appendChild(sellingPrice);

                Element cost = document.createElement("cost");
                cost.appendChild(document.createTextNode(String.format("%.2f", totalCost)));
                item.appendChild(cost);

            }

            Element total = document.createElement("total");
            total.appendChild(document.createTextNode("Rs. " + String.format("%.2f", finalBill)));
            root.appendChild(total);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
        //System.out.println(domToString(domSource));
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
        System.out.println("bye");
            transformer.transform(domSource, streamResult);
        System.out.println("hey");
    }

    // Get date in required format
    private static String getDate() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date dateobj = new Date();
        String date = df.format(dateobj);
        return date;
    }

    // Get time in required format
    private static String getTime() {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date dateobj = new Date();
        String time = df.format(dateobj);
        return time;
    }

}