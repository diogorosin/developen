package developen.common.persistence.type;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import developen.common.persistence.dpa.DPA;
import developen.common.persistence.query.Query;
import developen.common.persistence.session.Session;



public class ProxyList<E> implements List<E>, Proxy, Serializable {


	private static final long serialVersionUID = 1412535293368682849L;

	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	private Object[] elementData;

	private boolean initialized = false;

	private int size = 0;

	private Query query;


	public ProxyList(Query query){

		setQuery(query);

	}


	private Object[] getElementData() {


		if (elementData==null){

			elementData = new Object[0];

			if (!isInitialized()){

				try {

					Session session = DPA.getSessionFactory().createSession();

					List<Object> mylist = session.list(getQuery());

					session.close();

					elementData = Arrays.copyOf(elementData, size + mylist.size());

					for (Object object : mylist)

						elementData[size++] = object;

					setInitialized(true);

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		}		

		return elementData;


	}


	private void setElementData(Object[] elementData) {

		this.elementData = elementData;

	}


	public int size() {

		return getElementData().length;

	}


	public boolean isEmpty() {

		return getElementData().length == 0;

	}


	public boolean contains(Object o) {

		return indexOf(o) >= 0;

	}


	public Object[] toArray() {

		return Arrays.copyOf(getElementData(), size);

	}


	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {


		getElementData();

		if (a.length < size)

			return (T[]) Arrays.copyOf(getElementData(), size, a.getClass());

		System.arraycopy(getElementData(), 0, a, 0, size);

		if (a.length > size)

			a[size] = null;

		return a;


	}


	public boolean add(E e) {


		getElementData();

		ensureCapacity(size + 1);

		getElementData()[size++] = e;

		return true;


	}


	public boolean remove(Object o) {


		getElementData();

		if (o == null) {

			for (int index = 0; index < size; index++)

				if (getElementData()[index] == null) {

					fastRemove(index);

					return true;

				}

		} else {

			for (int index = 0; index < size; index++)

				if (o.equals(getElementData()[index])) {

					fastRemove(index);

					return true;

				}

		}		

		return false;


	}


	private void fastRemove(int index) {


		int numMoved = size - index - 1;

		if (numMoved > 0)

			System.arraycopy(getElementData(), index+1, getElementData(), index, numMoved);

		getElementData()[--size] = null;


	}


	public boolean containsAll(Collection<?> c) {


		for (Object e : c)

			if (!contains(e))

				return false;

		return true;


	}


	public boolean addAll(Collection<? extends E> c) {


		getElementData();

		Object[] a = c.toArray();

		int numNew = a.length;

		ensureCapacity(size + numNew);

		System.arraycopy(a, 0, getElementData(), size, numNew);

		size += numNew;

		return numNew != 0;


	}


	public boolean addAll(int index, Collection<? extends E> c) {


		getElementData();

		Object[] a = c.toArray();

		int numNew = a.length;

		ensureCapacity(size + numNew); 

		int numMoved = size - index;

		if (numMoved > 0)

			System.arraycopy(getElementData(), index, getElementData(), index + numNew, numMoved);

		System.arraycopy(a, 0, getElementData(), index, numNew);

		size += numNew;

		return numNew != 0;


	}


	public boolean removeAll(Collection<?> c) {

		return batchRemove(c, false);

	}


	private boolean batchRemove(Collection<?> c, boolean complement) {


		final Object[] elementData = getElementData();

		int r = 0;

		int w = 0;

		boolean modified = false;

		try {

			for (; r < size; r++)

				if (c.contains(elementData[r]) == complement)

					elementData[w++] = elementData[r];

		} finally {

			if (r != size) {

				System.arraycopy(elementData, r, elementData, w, size - r);

				w += size - r;

			}

			if (w != size) {

				for (int i = w; i < size; i++)

					elementData[i] = null;

				size = w;

				modified = true;

			}

		}

		return modified;


	}


	public boolean retainAll(Collection<?> c) {

		return batchRemove(c, true);

	}


	public void clear() {


		getElementData();

		for (int i = 0; i < size; i++)

			getElementData()[i] = null;

		size = 0;


	}


	@SuppressWarnings("unchecked")
	public E get(int index) {

		return (E) getElementData()[index];

	}


	@SuppressWarnings("unchecked")
	public E set(int index, E element) {


		E oldValue = (E) getElementData()[index];

		getElementData()[index] = element;

		return oldValue;


	}


	public void add(int index, E element) {


		getElementData();

		ensureCapacity(size + 1);

		System.arraycopy(getElementData(), index, getElementData(), index + 1,size - index);

		getElementData()[index] = element;

		size++;


	}


	@SuppressWarnings("unchecked")
	public E remove(int index) {


		E oldValue = (E) getElementData()[index];

		int numMoved = size - index - 1;

		if (numMoved > 0)

			System.arraycopy(getElementData(), index+1, getElementData(), index, numMoved);

		getElementData()[--size] = null;

		return oldValue;


	}


	public int indexOf(Object o) {


		getElementData();

		if (o == null) {

			for (int i = 0; i < size; i++)

				if (getElementData()[i]==null)

					return i;

		} else {

			for (int i = 0; i < size; i++)

				if (o.equals(getElementData()[i]))

					return i;

		}

		return -1;


	}


	public int lastIndexOf(Object o) {


		getElementData();

		if (o == null) {

			for (int i = size-1; i >= 0; i--)

				if (getElementData()[i]==null)

					return i;

		} else {

			for (int i = size-1; i >= 0; i--)

				if (o.equals(getElementData()[i]))

					return i;

		}

		return -1;


	}


	private static int hugeCapacity(int minCapacity) {


		if (minCapacity < 0) 

			throw new OutOfMemoryError();

		return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;


	}


	private void ensureCapacity(int minCapacity) {


		if (minCapacity - getElementData().length > 0)

			grow(minCapacity);


	}


	private void grow(int minCapacity) { 


		int oldCapacity = getElementData().length;

		int newCapacity = oldCapacity + (oldCapacity >> 1);

		if (newCapacity - minCapacity < 0)

			newCapacity = minCapacity;

		if (newCapacity - MAX_ARRAY_SIZE > 0)

			newCapacity = hugeCapacity(minCapacity);

		setElementData(Arrays.copyOf(getElementData(), newCapacity));


	}


	public List<E> subList(int fromIndex, int toIndex) {

		return null;

	}   


	public Iterator<E> iterator() {


		getElementData();

		return new MyIterator();


	}


	public ListIterator<E> listIterator() {


		getElementData();

		return new MyListIterator(0);


	}


	public ListIterator<E> listIterator(int index) {


		getElementData();

		if (index < 0 || index > size)

			throw new IndexOutOfBoundsException("Index: " + index);

		return new MyListIterator(index);


	}


	public Boolean isInitialized() {

		return initialized;

	}


	public void setInitialized(Boolean initialized) {

		this.initialized = initialized;

	}


	public Query getQuery() {

		return query;

	}


	public void setQuery(Query query) {

		this.query = query;

	}


	private class MyIterator implements Iterator<E> {


		int cursor;

		int lastRet = -1;

		public boolean hasNext() {

			return cursor != size;

		}

		@SuppressWarnings("unchecked")
		public E next() {

			int i = cursor;

			if (i >= size)

				throw new NoSuchElementException();

			Object[] elementData = ProxyList.this.getElementData();

			if (i >= elementData.length)

				throw new ConcurrentModificationException();

			cursor = i + 1;

			return (E) elementData[lastRet = i];

		}

		public void remove() {

			if (lastRet < 0)

				throw new IllegalStateException();

			try {

				ProxyList.this.remove(lastRet);

				cursor = lastRet;

				lastRet = -1;

			} catch (IndexOutOfBoundsException ex) {

				throw new ConcurrentModificationException();

			}

		}


	}


	private class MyListIterator extends MyIterator implements ListIterator<E> {

		public MyListIterator(int index) {

			cursor = index;

		}

		public boolean hasPrevious() {

			return cursor != 0;

		}

		public int nextIndex() {

			return cursor;

		}

		public int previousIndex() {

			return cursor - 1;

		}

		@SuppressWarnings("unchecked")
		public E previous() {

			int i = cursor - 1;

			if (i < 0)

				throw new NoSuchElementException();

			Object[] elementData = ProxyList.this.getElementData();

			if (i >= elementData.length)

				throw new ConcurrentModificationException();

			cursor = i;

			return (E) elementData[lastRet = i];

		}

		public void set(E e) {

			if (lastRet < 0)

				throw new IllegalStateException();

			try {

				ProxyList.this.set(lastRet, e);

			} catch (IndexOutOfBoundsException ex) {

				throw new ConcurrentModificationException();

			}

		}

		public void add(E e) {

			try {

				int i = cursor;

				ProxyList.this.add(i, e);

				cursor = i + 1;

				lastRet = -1;

			} catch (IndexOutOfBoundsException ex) {

				throw new ConcurrentModificationException();

			}

		}

	}


}
