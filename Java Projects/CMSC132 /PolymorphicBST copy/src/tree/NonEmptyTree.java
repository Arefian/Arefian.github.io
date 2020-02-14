package tree;

import java.util.Collection;


/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 *  
 */

	

 public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {
		private K key;
		private V value;
		private Tree<K,V> left;
		private Tree<K,V> right;
	
	/* Provide whatever instance variables you need */

	/**
	 * Only constructor we need.
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */
	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
		
	}

	public V search(K key) {
		//Base case: keys are matching
	     if (this.key.compareTo(key) == 0) {
             return this.value;
     } 		//if value is greater can current key value, go down right tree
	     else if (this.key.compareTo(key) < 0) {
             return right.search(key);
     } 
     	//otherwise value must be less than key value, go down left tree
             return left.search(key);

	}    


	public NonEmptyTree<K, V> insert(K key, V value) {
		 
		 //Base Case:  if key values are the same, assign the value
         if (this.key.compareTo(key) == 0) {
                 this.value = value;
                 //return this;
         }
         else if (this.key.compareTo(key) < 0) {
                 this.right = this.right.insert(key, value);
                // return this;
         }
         else {
                 this.left = this.left.insert(key, value);
                // return this;
         
         }
         //return the value of the inserted tree
         return this;
	}

	
	public Tree<K, V> delete(K key) {
		if (this.key.compareTo(key) != 0) {
			
			if (this.key.compareTo(key) > 0) {
				left = left.delete(key);
			} else if (this.key.compareTo(key) < 0) {
				right = right.delete(key);
			}
		} else{
			K newKey;
			V newValue;
			try {
				//if left tree is empty we get error, so check with try
				newKey = left.max();
				newValue = left.search(newKey);
				this.key = newKey;
				value = newValue;
				left = left.delete(newKey);
			} catch (TreeIsEmptyException e) {
				return this.right;
			}
		}
		return this;
}
	

	public K max() {
		  try {
              return this.right.max();
      } catch (TreeIsEmptyException e) {
              return key;
      }
	}

	public K min() {
		   try {
               return this.left.min();
       } catch (TreeIsEmptyException e) {
               return key;
       }
	}

	public int size() {
	return 	left.size() + right.size() + 1;  
	}
	
	

	public void addKeysToCollection(Collection<K> c) {	
		//add keys to left
		left.addKeysToCollection(c);
		//add current key
		c.add(this.key);
        //add keys to right
        right.addKeysToCollection(c);
	}
	
	

	public Tree<K,V> subTree(K fromKey, K toKey) {
		if (this.key.compareTo(fromKey) < 0) {
			return right.subTree(fromKey, toKey);
		} else if (this.key.compareTo(toKey) > 0) {
			return left.subTree(fromKey, toKey);
		}

		return new NonEmptyTree<K, V>(key, value, left.subTree(fromKey,toKey),
				right.subTree(fromKey, toKey));

	}
	
	public int height() {
		
		int rightTree = right.size();
		int leftTree = left.size();
		 if(leftTree >= rightTree) {
			return leftTree +1;
		}
		return rightTree +1;
	}
	
	public void inorderTraversal(TraversalTask<K,V> p) {
        left.inorderTraversal(p);
        p.performTask(this.key, this.value);
        right.inorderTraversal(p);
	}
	
	public void rightRootLeftTraversal(TraversalTask<K,V> p) {
        right.rightRootLeftTraversal(p);
        p.performTask(this.key, this.value);
        left.rightRootLeftTraversal(p);
	}	
}