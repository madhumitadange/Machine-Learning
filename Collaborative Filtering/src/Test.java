/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *  @author Nhan Nguyen
 *  CS 2336.002
 *  Professor: Cankaya
 *  Create generic stack
 */

class Stack<E>{
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();

    public int getSize() {
        return list.size();
    }

    public E peek() {
        return list.get(getSize() - 1);
    }

    public void push(E o) {
        list.add(o);
    }

    public E pop() {
        E o = list.get(getSize() - 1);
        list.remove(getSize() - 1);
        return o;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
  
    @Override
    public String toString() {
	return "Stack: " + list.toString();
    }
  
	
	
	

	}	


	
