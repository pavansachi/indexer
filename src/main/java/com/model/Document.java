package com.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * a comparable document
 * @author pavansachi
 *
 */
public class Document implements Comparable<Document> {

	public Document() {
	}

	public Document(String name) {
		this.name = name;
	}

	String name;

	public String getName() {
		return name;
	}

	List<Index> indexes = new ArrayList<>();

	public void add(String k, Object v) {

		Index idx = new Index(k, v);
		indexes.add(idx);
	}

	@Override
	public int compareTo(Document o) {

		Document thisDoc = this;

		Collection<Index> copy = new ArrayList<Index>( o.indexes );

		copy.removeAll(thisDoc.indexes);

		if (o.indexes.size() - copy.size() == thisDoc.indexes.size()) {
			return 0;
		}

		return -1;
	}

}