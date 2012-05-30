package br.ccsl.cogroo.analyzer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.Span;

import org.junit.Before;
import org.junit.Test;

import br.ccsl.cogroo.text.Sentence;
import br.ccsl.cogroo.text.Token;
import br.ccsl.cogroo.text.impl.DocumentImpl;
import br.ccsl.cogroo.text.impl.SentenceImpl;
import br.ccsl.cogroo.text.impl.TokenImpl;

public class POSTaggerTest {
  private POSTagger tagger;
  POSTaggerME mockedTagger;

  @Before
  public void setUp() throws Exception {
    mockedTagger = mock(POSTaggerME.class);
    tagger = new POSTagger(mockedTagger);
  }

  @Test
  public void testAnalyze() throws FileNotFoundException {
    DocumentImpl document = new DocumentImpl();

    String text = "A menina pequena foi para o seu quarto .";
    document.setText(text);

    String[] textArray = text.split(" ");

    Sentence sentence = new SentenceImpl(new Span(0, 40), document);
    document.setSentences(Collections.singletonList(sentence));

    List<Token> tokens = createTokens(textArray);
    sentence.setTokens(tokens);

    String[] tags = { "art", "n", "adj", "v-fin", "prp", "art", "pron-det",
        "n", "punc" };

    when(mockedTagger.tag(any(String[].class), any(Object[].class))).thenReturn(tags);
    tagger.analyze(document);

    assertEquals("art", document.getSentences().get(0).getTokens().get(0)
        .getPOSTag());
    assertEquals("n", document.getSentences().get(0).getTokens().get(1)
        .getPOSTag());
    assertEquals("adj", document.getSentences().get(0).getTokens().get(2)
        .getPOSTag());
    assertEquals("v-fin", document.getSentences().get(0).getTokens().get(3)
        .getPOSTag());
    assertEquals("prp", document.getSentences().get(0).getTokens().get(4)
        .getPOSTag());
    assertEquals("art", document.getSentences().get(0).getTokens().get(5)
        .getPOSTag());
    assertEquals("pron-det", document.getSentences().get(0).getTokens().get(6)
        .getPOSTag());
    assertEquals("n", document.getSentences().get(0).getTokens().get(7)
        .getPOSTag());
    assertEquals("punc", document.getSentences().get(0).getTokens().get(8)
        .getPOSTag());
  }

  private List<Token> createTokens(String[] textArray) {
    List<Token> tokens = new ArrayList<Token>();
    int ini = 0;

    for (int i = 0; i < textArray.length; i++) {
      Span span = new Span(ini, ini + textArray[i].length());
      TokenImpl tokenImpl = new TokenImpl(span, textArray[i]);
      ini = ini + textArray[i].length() + 1;
      tokens.add(tokenImpl);
    }
    return tokens;
  }
}