package com.jakub.huffman.model;

public class HuffmanLeaf extends HuffmanTree {

	// the character this leaf represents
	public final char value;

	public HuffmanLeaf(int freq, char val) {
		super(freq);
		value = val;
	}
}