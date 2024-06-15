package sortings;

import java.util.PriorityQueue;

import main.Sort;

public class IntroSort extends Sort {
	public IntroSort(int[] list) {
		super(list);
	}

	@Override
	public void sort() {
		int[] list = super.getList();
		int depth = (int) (Math.log(list.length) * 2);

		intro(list, depth, 0, list.length - 1);
	}

	private void intro(int[] list, int depth, int start, int end) {
		if (list.length <= 1) {
			return;
		} else if (depth == 0) {
			if (end - start > 16) {
				heapsort(list, start, end);
			} else {
				insertionsort(list, start, end);
			}
		} else {
			if (start >= end) {
				return;
			}
			int pivot = partition(list, start, end);
			intro(list, depth - 1, start, pivot);
			intro(list, depth - 1, pivot + 1, end);
		}

	}

	private void insertionsort(int[] list, int start, int end) {
		for (var i = start; i < end; i++) {
			var target = i;
			for (var j = i - 1; j >= 0; j--) {
				if (list[target] < list[j]) {
					swap(target--, j);
				} else {
					break;
				}
			}
		}
	}

	private void heapsort(int[] list, int start, int end) {
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		for (int i = start; i < end; i++) {
			heap.add(list[i]);
		}
		for (int i = start; i < end; i++) {
			list[i] = heap.poll();
		}
	}

	private int partition(int[] list, int start, int end) {
		int pivot = list[(start + end) / 2];
		while (true) {
			while (list[start] < pivot)
				start++;
			while (list[end] > pivot && start <= end)
				end--;
			if (start >= end)
				return end;
			swap(start, end);
		}
	}

}
