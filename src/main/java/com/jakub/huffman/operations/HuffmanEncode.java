package com.jakub.huffman.operations;

import java.util.Map;

public class HuffmanEncode {
	public static String encode(Map<Character, String> codes, String msg) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < msg.length(); i++) {
			char ch = msg.charAt(i);
			result.append(codes.get(ch));
		}
		return result.toString();
	}
}
