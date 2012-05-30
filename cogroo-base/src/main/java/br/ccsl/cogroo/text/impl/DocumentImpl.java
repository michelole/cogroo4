package br.ccsl.cogroo.text.impl;

import java.util.List;

import br.ccsl.cogroo.text.Document;
import br.ccsl.cogroo.text.Sentence;


/**
 * The <code>Document</code> class contains a text given by the user and also
 * its sentences separately in a list.
 */
public class DocumentImpl implements Document {

  /** the <code>String</code> which contains the whole text */
  private String text;

  /** the list of every sentence in <code>text</code> */
  private List<Sentence> sentences;

  public DocumentImpl() {
    // TODO Auto-generated constructor stub
  }
  
  /* (non-Javadoc)
   * @see br.ccsl.cogroo.text.Document#getText()
   */
  public String getText() {
    return text;
  }

  /* (non-Javadoc)
   * @see br.ccsl.cogroo.text.Document#setText(java.lang.String)
   */
  public void setText(String text) {
    this.text = text;
  }

  /* (non-Javadoc)
   * @see br.ccsl.cogroo.text.Document#getSentences()
   */
  public List<Sentence> getSentences() {
    return sentences;
  }

  /* (non-Javadoc)
   * @see br.ccsl.cogroo.text.Document#setSentences(java.util.List)
   */
  public void setSentences(List<Sentence> sentences) {
    this.sentences = sentences;
  }
}
