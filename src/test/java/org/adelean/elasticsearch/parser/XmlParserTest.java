package org.adelean.elasticsearch.parser;

import org.adelean.elasticsearch.model.Product;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class XmlParserTest {
    @Mock
    private XMLInputFactory xmlInputFactory;

    @Mock
    private XMLStreamReader xmlStreamReader;

    @InjectMocks
    private XmlParser xmlParser;

    @Test
    void should_parse_valid_xml() throws XMLStreamException {
        // Arrange
        String xmlContent = """
                <import>
                    <products>
                        <product>
                             <article_sku>
                             000c66dawt
                             </article_sku>
                        </product>
                    </products>
                </import>
                """;

        InputStream inputStream = new ByteArrayInputStream(xmlContent.getBytes());

        when(xmlInputFactory.createXMLStreamReader(inputStream)).thenReturn(xmlStreamReader);

        when(xmlStreamReader.hasNext()).thenReturn(true, true, true, true, true, true, false);
        when(xmlStreamReader.next()).thenReturn(
                XMLStreamReader.START_ELEMENT,
                XMLStreamReader.START_ELEMENT,
                XMLStreamReader.CHARACTERS,
                XMLStreamReader.END_ELEMENT,
                XMLStreamReader.END_ELEMENT,
                XMLStreamReader.END_ELEMENT
        );

        when(xmlStreamReader.getLocalName()).thenReturn("product", "article_sku", "product");
        when(xmlStreamReader.getText()).thenReturn("000c66dawt");

        // Act
        List<Product> products = xmlParser.parseXmlFile(inputStream);

        // Assert
        assertThat(products).hasSize(1);
        Product product = products.getFirst();
        AssertionsForClassTypes.assertThat(product.articleSku()).isEqualTo("000c66dawt");
    }

    @Test
    void should_return_empty_list_for_empty_xml() throws XMLStreamException {
        // Arrange
        String xmlContent = "<import><products></products></import>";
        InputStream inputStream = new ByteArrayInputStream(xmlContent.getBytes());

        when(xmlInputFactory.createXMLStreamReader(inputStream)).thenReturn(xmlStreamReader);
        when(xmlStreamReader.hasNext()).thenReturn(false);

        // Act
        List<Product> products = xmlParser.parseXmlFile(inputStream);

        // Assert
        assertThat(products).isEmpty();
    }

    @Test
    void should_handle_invalid_xml() throws XMLStreamException {
        // Arrange
        String invalidXmlContent = "<import><product></import>";
        InputStream inputStream = new ByteArrayInputStream(invalidXmlContent.getBytes());

        when(xmlInputFactory.createXMLStreamReader(inputStream)).thenThrow(new XMLStreamException("Invalid XML"));

        // Act
        List<Product> products = xmlParser.parseXmlFile(inputStream);

        // Assert
        assertThat(products).isEmpty();
    }

}