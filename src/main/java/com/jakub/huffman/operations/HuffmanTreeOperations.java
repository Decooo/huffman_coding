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

	// wejście to tablica częstotliwości, indeksowana kodem znaków
	public static HuffmanTree buildTree(int[] charFreqs) {
		PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
		for (int i = 0; i < charFreqs.length; i++)
			if (charFreqs[i] > 0)
				trees.offer(new HuffmanLeaf(charFreqs[i], (char) i));

		assert trees.size() > 0;

		// pętla, aż pozostanie tylko jedno drzewo
		while (trees.size() > 1) {
			// dwa drzewa o najmniejszej częstotliwości
			HuffmanTree a = trees.poll();
			HuffmanTree b = trees.poll();

			// wstaw do nowego węzła i włóż ponownie do kolejki
			trees.offer(new HuffmanNode(a, b));
		}
		return trees.poll();
	}

	public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
		assert tree != null;
		if (tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf) tree;

			// wypisz znak, częstotliwość i kod liścia
			System.out.println(leaf.value + "\t      " + leaf.frequency + "\t        " + prefix);

		} else if (tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode) tree;

			// przejdź rekursywnie w lewo
			prefix.append('0');
			printCodes(node.left, prefix);
			prefix.deleteCharAt(prefix.length() - 1);

			// przejdź rekursywnie w prawo
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

			// przejdź rekursywnie w lewo
			prefix.append('0');
			createHuffmanCodesMap(node.left, prefix);
			prefix.deleteCharAt(prefix.length() - 1);

			// przejdź rekursywnie w prawo
			prefix.append('1');
			createHuffmanCodesMap(node.right, prefix);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}
}
