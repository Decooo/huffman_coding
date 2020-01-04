package com.jakub.huffman.operations;

import com.jakub.huffman.model.HuffmanLeaf;
import com.jakub.huffman.model.HuffmanNode;
import com.jakub.huffman.model.HuffmanTree;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTreeOperations {
	private static Map<Character, String> mapHuffmanCodes = new HashMap<>();

	public static Map<Character, String> getMapHuffmanCodes() {
		return mapHuffmanCodes;
	}

	// input is an array of frequencies, indexed by character code
	public static HuffmanTree buildTree(int[] charFreqs) {
		PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
		for (int i = 0; i < charFreqs.length; i++)
			if (charFreqs[i] > 0)
				trees.offer(new HuffmanLeaf(charFreqs[i], (char) i));

		assert trees.size() > 0;

		// loop until there is only one tree left
		while (trees.size() > 1) {
			// two trees with least frequency
			HuffmanTree a = trees.poll();
			HuffmanTree b = trees.poll();

			// put into new node and re-insert into queue
			trees.offer(new HuffmanNode(a, b));
		}
		return trees.poll();
	}

	public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
		assert tree != null;
		if (tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf) tree;

			// print out character, frequency, and code for this leaf (which is just the prefix)
			System.out.println(leaf.value + "\t      " + leaf.frequency + "\t        " + prefix);

		} else if (tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode) tree;

			// traverse left
			prefix.append('0');
			printCodes(node.left, prefix);
			prefix.deleteCharAt(prefix.length() - 1);

			// traverse right
			prefix.append('1');
			printCodes(node.right, prefix);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	public static void createHuffmanCodesMap(HuffmanTree tree, StringBuffer prefix) {
		if (tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf) tree;
			mapHuffmanCodes.put(leaf.value, prefix.toString());
		} else if (tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode) tree;

			// traverse left
			prefix.append('0');
			createHuffmanCodesMap(node.left, prefix);
			prefix.deleteCharAt(prefix.length() - 1);

			// traverse right
			prefix.append('1');
			createHuffmanCodesMap(node.right, prefix);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}
}
