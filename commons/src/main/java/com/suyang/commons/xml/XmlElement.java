package com.suyang.commons.xml;

import com.suyang.commons.NameValue;

import javax.management.Attribute;
import java.util.ArrayList;
import java.util.List;

public class XmlElement extends Element {
    private List<NameValue<String>> attributes;

    private List<Element> elements;

    private String name;

    public XmlElement(String name) {
        super();
        attributes = new ArrayList<>();
        elements = new ArrayList<>();
        this.name = name;
    }

    public XmlElement(XmlElement original) {
        super();
        attributes = new ArrayList<>();
        attributes.addAll(original.attributes);
        elements = new ArrayList<>();
        elements.addAll(original.elements);
        this.name = original.name;
    }

    public List<NameValue<String>> getAttributes() {
        return attributes;
    }

    public void addAttribute(NameValue<String> attribute) {
        attributes.add(attribute);
    }

    public void addAttribute(String name, String value) {
        attributes.add(new NameValue<>(name, value));
    }

    public XmlElement withAttribute(String name, String value) {
        this.addAttribute(name, value);
        return this;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public void addElement(int index, Element element) {
        elements.add(index, element);
    }

    public String getName() {
        return name;
    }

    @Override
    public String getFormattedContent(int indentLevel) {
        StringBuilder sb = new StringBuilder();

        OutputUtilities.xmlIndent(sb, indentLevel);
        sb.append('<');
        sb.append(name);

        for (NameValue att : attributes) {
            sb.append(' ');
            sb.append(att.getName());
            sb.append("=\"");
            sb.append(att.getValue());
            sb.append('\"');
        }

        if (elements.size() > 0) {
            sb.append(" >");
            for (Element element : elements) {
                OutputUtilities.newLine(sb);
                sb.append(element.getFormattedContent(indentLevel + 1));
            }
            OutputUtilities.newLine(sb);
            OutputUtilities.xmlIndent(sb, indentLevel);
            sb.append("</");
            sb.append(name);
            sb.append('>');

        } else {
            sb.append(" />");
        }

        return sb.toString();
    }

    public void setName(String name) {
        this.name = name;
    }
}

