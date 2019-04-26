package com.portware.editor.trie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
 
/*
 * Auto-complete using tries in java
 */
public class Trie {
   
  public TrieNode root;
  public Trie(){
    root = new TrieNode(' ');
  }
   
  public void insertInTrie(String str){
    int strLen = str.length();
    if (strLen == 0) {
      root.isEndOfString = true;
    } else {
      int i=0;
      TrieNode current = root, child=null;
      while (i <strLen){
        child = current.subNode(str.toLowerCase().charAt(i));
        if (child == null){
          child = new TrieNode(str.toLowerCase().charAt(i));
          current.child.add(child);
        }
        current = child;
        i++;
      }
      current.isEndOfString  = true;
      current.leafNodeStr = str.toLowerCase();
    
    }
    
  }
   
  public TrieNode getLocationOfStringInTrie(String str){
    TrieNode current = this.root, child;
    int strLen = str.length();
    int i=0;
    while(i < strLen){
      child = current.subNode(str.toLowerCase().charAt(i));
      if (child != null) {
        current = child;
      } else {
        return null;
      }
      i++; 
    }
    if ( i == strLen){
      return current;
    }
    return null;
  }
   
  public List<String> getWorldList(TrieNode node){
     List<String> resultList = new ArrayList<>();
     
   return getAllWorldFromTrie(node, resultList);
     
         
  }
  
  private List<String> getAllWorldFromTrie(TrieNode node , List<String >wordList) {
	  if (node.isEndOfString) {
		  wordList.add(node.leafNodeStr);	      
	    }
	    for (TrieNode childNode : node.child){
	    	wordList = getAllWorldFromTrie(childNode ,wordList);
	    }
	    
	    return wordList ;
  }
   
  public boolean searchInTrie(String str){
    int strLen = str.length();
    if (strLen == 0){
      return true;
    }else {
      int i=0;
      TrieNode current = root, child =  null;
      while (i < strLen) {
        child = current.subNode(str.charAt(i));
        if (child != null) {
          current = child;
        }else {
          return false;
        }
        i++;
      }
      if (i == strLen) {
        return true;
      }
    }
    return false;
  }
     
  public class TrieNode {
    public char data;
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
    }
  }
   

 
}