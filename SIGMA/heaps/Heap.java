import java.util.*;

public class Heap {
    static class Heap1 {
        ArrayList<Integer> arr = new ArrayList<>();

        public void add(int data) {// o(logn)
            // add at last index as in arrayList we add data at end
            arr.add(data);// o(1)

            // find the parent index
            int x = arr.size() - 1;// x is child idx
            int par = (x - 1) / 2;// par index

            // fix heap
            while (arr.get(x) < arr.get(par)) {// o(logn)
                // swap
                int temp = arr.get(x);
                arr.set(x, arr.get(par));
                arr.set(par, temp);

                x = par;
                par = (x - 1) / 2;
            }
        }

        public int peek() {// o(1)
            return arr.get(0);
        }

        private void heapify(int i) {

        /*
			steps -> 
			-find left and right of the root
			-then find minIdx
			-check if its a laef nofe left/right < arr.size
            -and find the minimum arr.get(minIdx) > arr.get(left) like that
            -while check that if our minIdx change or not if it change then swap
            -heapify takes o(logn)
					
		*/
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int minIdx = i;

            if (left < arr.size() && arr.get(minIdx) > arr.get(left)) {
                minIdx = left;
            }
            if (right < arr.size() && arr.get(minIdx) > arr.get(right)) {
                minIdx = right;
            }
            if (minIdx != i) {
                // swap
                int temp = arr.get(i);
                arr.set(i, arr.get(minIdx));
                arr.set(minIdx, temp);
                heapify(minIdx);
            }
        }

        public int remove() {// o(logn)
            //in pq we remove from front or from 1st
            int removedElem = arr.get(0);

            // step 1 -> swap first & last
            int temp = arr.get(0);
            arr.set(0, arr.get(arr.size() - 1));
            arr.set(arr.size() - 1, temp);

            // step -> 2 remove the ele beacause in al we from last small wil be remove after swapping
            arr.remove(arr.size() - 1);

            // step -> 3 hepify
            heapify(0);
            return removedElem;
        }

        public boolean isEmpty() {
            return arr.size() == 0;
        }

    }

    public static void main(String[] args) {
        Heap1 h = new Heap1();
        h.add(3);
        h.add(4);
        h.add(1);
        h.add(5);
        while (!h.isEmpty()) {
            System.out.println(h.peek());
            h.remove();
        }
    }
}


//heap sort(nlogn)
//for map heap change three sign on in add function and other two in heapify last after &&