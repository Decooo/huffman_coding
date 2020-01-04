package com.jakub.huffman.operations;

import com.jakub.huffman.model.HuffmanLeaf;
import com.jakub.huffman.model.HuffmanNode;
import com.jakub.huffman.model.HuffmanTree;

public class HuffmanDecode {
	public static String decode(HuffmanTree root, String encodedString) {
		StringBuilder result = new StringBuilder();

		if (root instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode) root;

			for (int i = 0; i < encodedString.length(); i++) {
				HuffmanLeaf leaf = null;
				if (encodedString.charAt(i) == '0') {
					if (node.left instanceof HuffmanNode)
						node = (HuffmanNode) node.left;
					else
						leaf = (HuffmanLeaf) node.left;
				} else {
					if (node.right instanceof HuffmanNode)
						node = (HuffmanNode) node.right;
					else
						leaf = (HuffmanLeaf) node.right;
				}

				if (leaf != null) {
					result.append(leaf.value);
					node = (HuffmanNode) root;
				}
			}
		}
		return result.toString() + '\0';
	}
}
