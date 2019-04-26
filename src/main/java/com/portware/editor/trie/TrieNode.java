package com.portware.editor.trie;

import java.util.Collection;
import java.util.LinkedList;

class TrieNode {  public char data;
public boolean isEndOfString;
public Collection<TrieNode> child;
public String leafNodeStr;
 
public TrieNode(char data){
  this.data = data;
  child = new LinkedList<TrieNode>();
  this.isEndOfString = false;
}
 
public TrieNode subNode(char data){
  if (child != null) {
    for (TrieNode childNode : child) {
      if (childNode.data == data){
        return childNode;
      }
    }
  }
  return null;
}}
