package com.tlyy.util;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.SAXContentHandler;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.LocatorImpl;

import com.tlyy.log.LogUtil;

public class XMLUtil {
	public final static SAXReader reader;
	static{
		XMLUtil xmlUtil =new XMLUtil();
		Locator locator = new LocatorImpl();  
		DocumentFactory docFactory = xmlUtil.new DocumentFactoryWithLocator(locator);  
		reader = xmlUtil.new GokuSAXReader(docFactory, locator); 
	}
	
	class GokuElement extends DefaultElement{
		private static final long serialVersionUID = 21345732721122890L;
		private int lineNum;
		private int colNum;
		public GokuElement(QName qname){
			super(qname);
		}
		public GokuElement(QName qname, int attrCount) {  
		    super(qname, attrCount);  
		}  
		  
		public GokuElement(String name) {  
		    super(name);  
		}  
		  
		public GokuElement(String name, Namespace namespace) {  
		    super(name, namespace);  
		}  
		  
	    public int getColumnNumber() {  
	        return this.colNum;  
	    }  
		  
	    public int getLineNumber() {  
	        return this.lineNum;  
	    } 
	    
	    public void setLocation(int lineNum, int colNum){
	    	this.lineNum = lineNum;
	    	this.colNum = colNum;
	    }
		
	}
	
	
	
	class DocumentFactoryWithLocator extends DocumentFactory {
		private static final long serialVersionUID = -1661524679602470672L;
		private Locator locator;
		
		public DocumentFactoryWithLocator(Locator locator){
			super();
			this.locator = locator;
		}
		
	    @Override
	    public Element createElement(QName qname){
			GokuElement element = new GokuElement(qname);
			element.setLocation(this.locator.getLineNumber(),this.locator.getColumnNumber());
	    	return element;	
	    }
	    
	    @Override
	    public Element createElement(String name){
	    	GokuElement element = new GokuElement(name);
	    	element.setLocation(this.locator.getLineNumber(),this.locator.getColumnNumber());
	    	return element;
	    }
	    
	    public void setLocator(Locator locator){
	    	this.locator = locator;
	    }
	}
	
	
	class GokuSAXContentHandler extends SAXContentHandler{
		private DocumentFactoryWithLocator documentFacotry;
		
		public GokuSAXContentHandler(DocumentFactory documentFacotry,ElementHandler dispatchHandler){
			super(documentFacotry, dispatchHandler);
		}
		public void setDocFactory(DocumentFactoryWithLocator  documentFactoryWithLocator){
			this.documentFacotry=documentFactoryWithLocator;
		}
		
		@Override 
		public void setDocumentLocator(Locator documentLocator){
			super.setDocumentLocator(documentLocator);
			if(this.documentFacotry !=null){
				this.documentFacotry.setLocator(documentLocator);
			}
		}
	}
	
	class GokuSAXReader extends SAXReader{
		DocumentFactory documentFactory;
		Locator locator;
	
		public GokuSAXReader(DocumentFactory documentFactory, Locator locator){
			super(documentFactory);
			this.locator = locator;
			this.documentFactory=documentFactory;
		}
		
		@Override
		protected SAXContentHandler createContentHandler(XMLReader reader){
			return new GokuSAXContentHandler(this.getDocumentFactory(),super.getDispatchHandler());
		}
		
		@Override
		public Document read(InputSource in) throws DocumentException{
			try {  
	            XMLReader reader = this.getXMLReader();  
	  
	            reader = this.installXMLFilter(reader);  
	  
	            EntityResolver thatEntityResolver = super.getEntityResolver();  
	  
	            if (thatEntityResolver == null) {  
	                thatEntityResolver = this.createDefaultEntityResolver(in.getSystemId());  
	                super.setEntityResolver(thatEntityResolver);  
	            }  
	  
	            reader.setEntityResolver(thatEntityResolver);  
	  
	            SAXContentHandler contentHandler = this.createContentHandler(reader);  
	            contentHandler.setEntityResolver(thatEntityResolver);  
	            contentHandler.setInputSource(in);  
	  
	            boolean internal = this.isIncludeInternalDTDDeclarations();  
	            boolean external = this.isIncludeExternalDTDDeclarations();  
	  
	            contentHandler.setIncludeInternalDTDDeclarations(internal);  
	            contentHandler.setIncludeExternalDTDDeclarations(external);  
	            contentHandler.setMergeAdjacentText(this.isMergeAdjacentText());  
	            contentHandler.setStripWhitespaceText(this.isStripWhitespaceText());  
	            contentHandler.setIgnoreComments(this.isIgnoreComments());  
	            reader.setContentHandler(contentHandler);  
	  
	            this.configureReader(reader, contentHandler);  
	            ((GokuSAXContentHandler) contentHandler).setDocFactory((DocumentFactoryWithLocator) this.documentFactory);  
	            contentHandler.setDocumentLocator(this.locator);  
	            reader.parse(in);  
	  
	            return contentHandler.getDocument();  
	        } catch (Exception e) {  
	            if (e instanceof SAXParseException) {  
	                // e.printStackTrace();  
	                SAXParseException parseException = (SAXParseException) e;  
	                String systemId = parseException.getSystemId();  
	  
	                if (systemId == null) {  
	                    systemId = "";  
	                }  
	  
	                String message = "Error on line " + parseException.getLineNumber() + " of document " + systemId + " : "  
	                        + parseException.getMessage();  
	  
	                throw new DocumentException(message, e);  
	            } else {  
	                throw new DocumentException(e.getMessage(), e);  
	            }  
	        }  
	    }  
	}


	public void readXMLByNIO(String filePath,String fileName) {
		try {
			RandomAccessFile aFile = new RandomAccessFile(filePath+fileName,"rw");
			FileChannel inChannel = aFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(15);
			int bytesRead = inChannel.read(buf);
			while (bytesRead != -1) {
				LogUtil.debug("Read ",bytesRead);
				buf.flip();
				while (buf.hasRemaining()) {
					LogUtil.debug((char) buf.get());
				}
				buf.clear();
				bytesRead = inChannel.read(buf);
			}
			aFile.close();

		} catch (FileNotFoundException e) {
			LogUtil.error(e);
		} catch (Exception e) {
			LogUtil.error(e);
		}

	}
}
