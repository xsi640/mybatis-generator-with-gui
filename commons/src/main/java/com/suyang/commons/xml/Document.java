package com.suyang.commons.xml;

public class Document {
    private String publicId;

    private String systemId;

    private XmlElement rootElement;

    public Document(String publicId, String systemId) {
        super();
        this.publicId = publicId;
        this.systemId = systemId;
    }

    public Document() {
        super();
    }

    public XmlElement getRootElement() {
        return rootElement;
    }

    public void setRootElement(XmlElement rootElement) {
        this.rootElement = rootElement;
    }

    public String getPublicId() {
        return publicId;
    }

    public String getSystemId() {
        return systemId;
    }

    public String getFormattedContent() {
        StringBuilder sb = new StringBuilder();

        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

        if (publicId != null && systemId != null) {
            OutputUtilities.newLine(sb);
            sb.append("<!DOCTYPE ");
            sb.append(rootElement.getName());
            sb.append(" PUBLIC \"");
            sb.append(publicId);
            sb.append("\" \"");
            sb.append(systemId);
            sb.append("\" >");
        }

        OutputUtilities.newLine(sb);
        sb.append(rootElement.getFormattedContent(0));

        return sb.toString();
    }
}
