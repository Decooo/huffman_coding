package com.jakub.huffman;

import com.jakub.huffman.model.HuffmanTree;
import com.jakub.huffman.operations.HuffmanDecode;
import com.jakub.huffman.operations.HuffmanEncode;
import com.jakub.huffman.operations.HuffmanTreeOperations;

import java.math.BigInteger;
import java.util.ArrayList;

public class HuffmanCode {


	public static void main(String[] args) {
		String msg = "To jest przyklad wiadomosci ktora zostanie poddana zakodowaniu algorytmem huffmana";

		// założmy, że wszystkie nasze znaki będą miały kod mniejszy niż 256, dla uproszczenia (kod ASCII)
		int[] charFreqs = new int[256];
		// przeczytaj każdy znak i zapisz częstotliwości
		for (char ch : msg.toCharArray())
			charFreqs[ch]++;

		HuffmanTree tree = HuffmanTreeOperations.buildTree(charFreqs);

		// Wypisanie wyników
		System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
		HuffmanTreeOperations.printCodes(tree, new StringBuffer());
		HuffmanTreeOperations.createHuffmanCodesMap(tree, new StringBuffer());

		StringBuilder encodeString = new StringBuilder(HuffmanEncode.encode(HuffmanTreeOperations.getMapHuffmanCodes(), msg));

		System.out.println("Zakodowana wiadomość = " + encodeString);

		String decodeString = HuffmanDecode.decode(tree, encodeString.toString());
		System.out.println("Zdekodowana wiadomość = " + decodeString);

		String binary = new BigInteger(msg.getBytes()).toString(2);
		System.out.println("Oryginalna wiadomość binarnie: " + binary);

		System.out.println("Długość zakodowanej wiadomości w bitach = " + encodeString.length());
		System.out.println("Długość oryginalnej wiadomości w bitach = " + binary.length());

		System.out.println("Stary tekst = " + msg);
		changeToNewAsciiChar(encodeString);
	}

	/*
	Konwersja na nowe znaki z tabeli ascii
	Na pierwszym polu zostaje dodana liczba o jaką należy przesunąć pozostałe bity, aby poprawnie zdekodować za pomocą drzewa huffmana
	Przykład: ciąg ALA ma kod huffmana 101101, aby miał 8 bit dopisujemy z przodu dwa zera i powstaje nam 00101101
	teraz do gotowego teksu dopisujemy na początku cyfre 2, dekoder musi odczytać pierwszy znak (czyli 2) następnie odcina
	dwa pierwsze bity z reszty ciągu ciągu i resztę podstawia do zdekodowania za pomocą drzewa Huffmana
	*/
	private static void changeToNewAsciiChar(StringBuilder encodeString) {
		//uzupełnienie zerami na początku dla dopełnienia do 8 bitów, aby można skonwertować na kody ascii
		int countAddNumber = 0;
		while (encodeString.length() % 8 != 0) {
			countAddNumber++;
			encodeString.insert(0, "0");
		}

		//Podział ciągu na 8 bitowe podciągi tworzące liczby od 0 do 255 zapisane binarnie na 8 bitach
		ArrayList<String> list = new ArrayList<>();
		for (int i = 8; i < encodeString.length(); i += 8) {
			StringBuilder temp = new StringBuilder();
			for (int j = i - 8; j < i; j++) {
				temp.append(encodeString.charAt(j));
			}
			list.add(temp.toString());

		}

		// Konwersja na nowe znaki z tabeli ascii
		StringBuilder convertedList = new StringBuilder();
		convertedList.append(countAddNumber);
		for (String aList : list) {
			int code = new BigInteger(aList, 2).intValue();
			convertedList.append((char) code);
		}

		System.out.println("Nowy tekst = " + convertedList);
	}
}