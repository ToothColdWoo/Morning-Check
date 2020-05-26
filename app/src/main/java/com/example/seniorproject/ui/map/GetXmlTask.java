package com.example.seniorproject.ui.map;

import android.os.AsyncTask;
import android.util.Log;

import com.example.seniorproject.BuildConfig;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;
import org.xml.sax.InputSource;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class GetXmlTask extends AsyncTask<Double, Void, ArrayList> {
    double lat;
    double lon;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected ArrayList doInBackground(Double... doubles) {

        ArrayList<String> resultList = new ArrayList<>();

        ArrayList<String> centerYnList = new ArrayList<>();
        ArrayList<String> districtCdList = new ArrayList<>();
        ArrayList<String> mobileNoList = new ArrayList<>();
        ArrayList<String> regionNameList = new ArrayList<>();
        ArrayList<String> stationIdList = new ArrayList<>();
        ArrayList<String> stationNameList = new ArrayList<>();
        ArrayList<String> longitudeList = new ArrayList<>();
        ArrayList<String> latitudeList = new ArrayList<>();
        ArrayList<String> distanceList = new ArrayList<>();
        Document doc = new Document() {
            @Override
            public DocumentType getDoctype() {
                return null;
            }

            @Override
            public DOMImplementation getImplementation() {
                return null;
            }

            @Override
            public Element getDocumentElement() {
                return null;
            }

            @Override
            public Element createElement(String tagName) throws DOMException {
                return null;
            }

            @Override
            public DocumentFragment createDocumentFragment() {
                return null;
            }

            @Override
            public Text createTextNode(String data) {
                return null;
            }

            @Override
            public Comment createComment(String data) {
                return null;
            }

            @Override
            public CDATASection createCDATASection(String data) throws DOMException {
                return null;
            }

            @Override
            public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
                return null;
            }

            @Override
            public Attr createAttribute(String name) throws DOMException {
                return null;
            }

            @Override
            public EntityReference createEntityReference(String name) throws DOMException {
                return null;
            }

            @Override
            public NodeList getElementsByTagName(String tagname) {
                return null;
            }

            @Override
            public Node importNode(Node importedNode, boolean deep) throws DOMException {
                return null;
            }

            @Override
            public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
                return null;
            }

            @Override
            public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
                return null;
            }

            @Override
            public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
                return null;
            }

            @Override
            public Element getElementById(String elementId) {
                return null;
            }

            @Override
            public String getInputEncoding() {
                return null;
            }

            @Override
            public String getXmlEncoding() {
                return null;
            }

            @Override
            public boolean getXmlStandalone() {
                return false;
            }

            @Override
            public void setXmlStandalone(boolean xmlStandalone) throws DOMException {

            }

            @Override
            public String getXmlVersion() {
                return null;
            }

            @Override
            public void setXmlVersion(String xmlVersion) throws DOMException {

            }

            @Override
            public boolean getStrictErrorChecking() {
                return false;
            }

            @Override
            public void setStrictErrorChecking(boolean strictErrorChecking) {

            }

            @Override
            public String getDocumentURI() {
                return null;
            }

            @Override
            public void setDocumentURI(String documentURI) {

            }

            @Override
            public Node adoptNode(Node source) throws DOMException {
                return null;
            }

            @Override
            public DOMConfiguration getDomConfig() {
                return null;
            }

            @Override
            public void normalizeDocument() {

            }

            @Override
            public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException {
                return null;
            }

            @Override
            public String getNodeName() {
                return null;
            }

            @Override
            public String getNodeValue() throws DOMException {
                return null;
            }

            @Override
            public void setNodeValue(String nodeValue) throws DOMException {

            }

            @Override
            public short getNodeType() {
                return 0;
            }

            @Override
            public Node getParentNode() {
                return null;
            }

            @Override
            public NodeList getChildNodes() {
                return null;
            }

            @Override
            public Node getFirstChild() {
                return null;
            }

            @Override
            public Node getLastChild() {
                return null;
            }

            @Override
            public Node getPreviousSibling() {
                return null;
            }

            @Override
            public Node getNextSibling() {
                return null;
            }

            @Override
            public NamedNodeMap getAttributes() {
                return null;
            }

            @Override
            public Document getOwnerDocument() {
                return null;
            }

            @Override
            public Node insertBefore(Node newChild, Node refChild) throws DOMException {
                return null;
            }

            @Override
            public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
                return null;
            }

            @Override
            public Node removeChild(Node oldChild) throws DOMException {
                return null;
            }

            @Override
            public Node appendChild(Node newChild) throws DOMException {
                return null;
            }

            @Override
            public boolean hasChildNodes() {
                return false;
            }

            @Override
            public Node cloneNode(boolean deep) {
                return null;
            }

            @Override
            public void normalize() {

            }

            @Override
            public boolean isSupported(String feature, String version) {
                return false;
            }

            @Override
            public String getNamespaceURI() {
                return null;
            }

            @Override
            public String getPrefix() {
                return null;
            }

            @Override
            public void setPrefix(String prefix) throws DOMException {

            }

            @Override
            public String getLocalName() {
                return null;
            }

            @Override
            public boolean hasAttributes() {
                return false;
            }

            @Override
            public String getBaseURI() {
                return null;
            }

            @Override
            public short compareDocumentPosition(Node other) throws DOMException {
                return 0;
            }

            @Override
            public String getTextContent() throws DOMException {
                return null;
            }

            @Override
            public void setTextContent(String textContent) throws DOMException {

            }

            @Override
            public boolean isSameNode(Node other) {
                return false;
            }

            @Override
            public String lookupPrefix(String namespaceURI) {
                return null;
            }

            @Override
            public boolean isDefaultNamespace(String namespaceURI) {
                return false;
            }

            @Override
            public String lookupNamespaceURI(String prefix) {
                return null;
            }

            @Override
            public boolean isEqualNode(Node arg) {
                return false;
            }

            @Override
            public Object getFeature(String feature, String version) {
                return null;
            }

            @Override
            public Object setUserData(String key, Object data, UserDataHandler handler) {
                return null;
            }

            @Override
            public Object getUserData(String key) {
                return null;
            }
        };
        try {
            String key = BuildConfig.BUS_KEY;
            String urlString = "http://openapi.gbis.go.kr/ws/rest/busstationservice/searcharound?serviceKey=" + key + "&x=" + doubles[0] + "&y=" + doubles[1];
            URL url = new URL(urlString);

            Log.d("tttt", url.toString());

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            doc = db.parse(urlString);
            doc.getDocumentElement().normalize();

        }
        catch (Exception e){
           e.printStackTrace();
        }

        NodeList nodeList = doc.getElementsByTagName("busStationAroundList");
        NodeList elementNumber = doc.getElementsByTagName("centerYn");

        int max =nodeList.getLength();

        for(int i = 0; i < max; i++){
            Node node = nodeList.item(i);
            Element fstElmnt = (Element) node;

            NodeList centerYn = fstElmnt.getElementsByTagName("centerYn");
            resultList.add(centerYn.item(0).getChildNodes().item(0).getNodeValue());

            NodeList districtCd = fstElmnt.getElementsByTagName("districtCd");
            resultList.add(districtCd.item(0).getChildNodes().item(0).getNodeValue());

            NodeList mobileNo = fstElmnt.getElementsByTagName("mobileNo");
            if (mobileNo.getLength() != 0) {
                resultList.add(mobileNo.item(0).getChildNodes().item(0).getNodeValue());
            } else {
                resultList.add( "NULL");
            }
            NodeList regionName = fstElmnt.getElementsByTagName("regionName");
            resultList.add( regionName.item(0).getChildNodes().item(0).getNodeValue());

            NodeList stationId = fstElmnt.getElementsByTagName("stationId");
            resultList.add( stationId.item(0).getChildNodes().item(0).getNodeValue());

            NodeList stationName = fstElmnt.getElementsByTagName("stationName");
            resultList.add( stationName.item(0).getChildNodes().item(0).getNodeValue());

            NodeList x = fstElmnt.getElementsByTagName("x");
            resultList.add( x.item(0).getChildNodes().item(0).getNodeValue());

            NodeList y = fstElmnt.getElementsByTagName("y");
            resultList.add( y.item(0).getChildNodes().item(0).getNodeValue());

            NodeList distance = fstElmnt.getElementsByTagName("distance");
            resultList.add( distance.item(0).getChildNodes().item(0).getNodeValue());

        }

        return resultList;
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
    }
}
