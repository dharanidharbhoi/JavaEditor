package com.portware.autocomplete;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.portware.editor.trie.Trie;

public class AutoCompleteUtility {
	private static Trie trie ;

	private AutoCompleteUtility() {
		throw new IllegalStateException("This is a utility class");
	}
	
	
	public static void loadkeyWords() {
		 trie = new Trie();
		 
		 try {
			List<File> fileList = Files.walk(Paths.get("C:\\Users\\dbhoi\\TypeSafe\\JavaEditor\\src\\main\\resources"))
					 .filter(Files::isRegularFile)
					 .map(Path::toFile)
					 .collect(Collectors.toList());
			if(!fileList.isEmpty()) {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(fileList.get(0)));
				String word ;
				while((word =bufferedReader.readLine()) != null) {
					trie.insertInTrie(word);
				}
				bufferedReader.close();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	public static List<String> getWordSuggestion(String word){
		if(trie.getLocationOfStringInTrie(word) == null) {
			return Collections.emptyList();
		}
		return trie.getWorldList(trie.getLocationOfStringInTrie(word));
		
	}
	

}
