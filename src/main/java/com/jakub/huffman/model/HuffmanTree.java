package com.jakub.huffman.model;

public abstract class HuffmanTree implements Comparable<HuffmanTree> {

	// the frequency of this tree
	public final int frequency;

	public HuffmanTree(int freq) {
		frequency = freq;
	}

	// compares on the frequency
	public int compareTo(HuffmanTree tree) {
		return frequency - tree.frequency;
	}
}
