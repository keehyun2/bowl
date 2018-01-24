package com.khphub;

import java.util.Arrays;

public class Sort {
	/**
	 * int 배열에서 2개 원소를 서로 교환합니다.
	 * 
	 * @param array
	 *            전달받은 배열
	 * @param fromIdx
	 * @param targetIdx
	 */
	void swap(int[] array, int fromIdx, int targetIdx) {
		int temp = array[targetIdx];
		array[targetIdx] = array[fromIdx];
		array[fromIdx] = temp;
	}

	/**
	 * 버블 정렬
	 * 
	 * @param arr
	 */
	void bubbleSort(int[] arr) {
		for (int i = arr.length - 1; i > 0; i--)
			for (int j = 0; j < i; j++)
				if (arr[j] > arr[j + 1])
					swap(arr, j, j + 1);
	}

	/**
	 * 선택정렬
	 * 
	 * @param arr
	 */
	void selectionSort(int[] arr) {
		for (int i = arr.length - 1; i > 0; i--) {
			int maxIndex = 0; // 배열 원소중 max 값의 index를 저장할 변수
			for (int j = 1; j <= i; j++)
				if (arr[j] > arr[maxIndex])
					maxIndex = j;
			swap(arr, i, maxIndex);
		}
	}

	/**
	 * 삽입
	 * 
	 * @param arr
	 */
	void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) { // i가 0이 아니라 1 부터 시작합니다. i가 0 인 loop 는 비교할 원소가 없습니다.
			int ai = arr[i];
			int j;
			for (j = i; j > 0 && arr[j - 1] > ai; j--)
				arr[j] = arr[j - 1];
			arr[j] = ai;
		}
	}

	/**
	 * 알고리즘은 삽입정렬하고 동일한데 shell 정렬용으로 코드가 조금 변경되었습니다.
	 * 
	 * @param array
	 *            배열
	 * @param startIndex
	 *            시작 인덱스
	 * @param gap
	 *            증분
	 */
	void iSort(int[] arr, int startIndex, int gap) {
		for (int i = startIndex + gap; i < arr.length; i += gap) {
			int ai = arr[i];
			int j;
			for (j = i; j > startIndex && arr[j - 1] > ai; j--)
				arr[j] = arr[j - 1];
			while (j > gap && arr[j - gap] > ai) {
				arr[j] = arr[j - gap];
				j -= gap;
			}
			arr[j] = ai;
		}
	}

	/**
	 * 쉘 정렬
	 * 
	 * @param arr
	 */
	void shellSort(int[] arr) {
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int startIndex = 0; startIndex < gap; startIndex++)
				iSort(arr, startIndex, gap);
		}
	}

	/**
	 * 버킷 정렬
	 * 
	 * @param a
	 * @param maxVal
	 */
	void bucketSort(int[] a, int maxVal) {
		int[] bucket = new int[maxVal + 1];

		for (int i = 0; i < bucket.length; i++) {
			bucket[i] = 0;
		}

		for (int i = 0; i < a.length; i++) {
			bucket[a[i]]++;
		}

		int outPos = 0;
		for (int i = 0; i < bucket.length; i++) {
			for (int j = 0; j < bucket[i]; j++) {
				a[outPos++] = i;
			}
		}
	}

	/**
	 * 비순환 드라이버 method
	 * 
	 * @param arr
	 */
	void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length);
	}

	/**
	 * 합병 정렬 - 분할하는 로직이 들어있고, 다른 합병정렬 알고리즘을 가지고 있는 함수를 실행합니다.
	 * 
	 * @param arr
	 *            배열
	 * @param startIndex
	 *            시작 index
	 * @param last
	 *            처음에 배열의 길이를 입력 받고, -1 해서 마지막 인덱스로 사용됩니다.
	 */
	void mergeSort(int[] arr, int startIndex, int last) {
		// 선조건 : 0 < startIndex < last <= arr.length
		// 후조건 : arr[startIndex...last-1]은 오름차순이다.
		if (last - startIndex < 2)
			return;
		int middleIdex = (last + startIndex) / 2; // 배열의 중간 index

		mergeSort(arr, startIndex, middleIdex); // [startIndex, middleIdex) 반열린구간
		mergeSort(arr, middleIdex, last); // [middleIdex, last) 반열린구간
		merge(arr, startIndex, middleIdex, last);

		// System.out.println(Arrays.toString(arr));
	}

	/**
	 * 합병하는 알고리즘입니다.
	 * 
	 * @param arr
	 * @param startIndex
	 * @param middleIdex
	 * @param last
	 */
	void merge(int[] arr, int startIndex, int middleIdex, int last) {
		// 선조건 : arr[startIndex...middleIdex-1] 과 arr[middleIdex...last-1] 은 오름차순이다.
		// 후조건 : arr[startIndex...last-1] 은 오름차순이다.
		if (arr[middleIdex - 1] <= arr[middleIdex])
			return;
		int i = startIndex, j = middleIdex, k = 0;
		int[] tempArr = new int[last - startIndex]; // 합병할때만 사용하는 임시 배열
		while (i < middleIdex && j < last) {
			if (arr[i] < arr[j])
				tempArr[k++] = arr[i++];
			else
				tempArr[k++] = arr[j++];
		}
		if (i < middleIdex)
			System.arraycopy(arr, i, arr, startIndex + k, middleIdex - i); // shift arr[i...midddleIndex - 1]
		System.arraycopy(tempArr, 0, arr, startIndex, k); // copy tempArray[0...k-1] to arr[p...p+k-1]
	}

	/**
	 * 비순환 드라이버 method
	 * 
	 * @param arr
	 */
	void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length);
	}

	/**
	 * 퀵정렬
	 * 
	 * @param arr
	 *            배열
	 * @param startIndex
	 *            시작 index
	 * @param last
	 *            처음에 배열의 길이를 입력 받고, -1 해서 마지막 인덱스로 사용됩니다.
	 */
	void quickSort(int[] arr, int startIndex, int last) {
		if (last - startIndex < 2)
			return;
		int j = partition(arr, startIndex, last);
		quickSort(arr, startIndex, j);
		quickSort(arr, j + 1, last);
	}

	/**
	 * 
	 * @param arr
	 * @param startIndex
	 * @param last
	 * @return pivot 의 index 를 반환합니다.
	 */
	int partition(int[] arr, int startIndex, int last) {

		int pivot = arr[startIndex]; // 맨 앞에 있는 원소를 pivot정한 경우, 이 부분이 성능을 결정합니다.
		int i = startIndex, j = last;
		while (i < j) { // i == j 가 되면 loop 가 종료됩니다. 회전할때 마다 i가 증가되거나 j가 감소 됩니다.
			while (j > i && arr[--j] >= pivot)
				; // empty loop, j는 pivot 이 될때까지 1을 선감소
			if (j > i)
				arr[i] = arr[j]; // arr[j]는 arr[i]로 복사되고, 빈공간이되었다고 봅니다.
			while (i < j && arr[++i] <= pivot)
				; // empty loop, i는 pivot 이 될때까지 1을 선 증가
			if (i < j)
				arr[j] = arr[i]; // arr[i]는 arr[j]로 복사되고, 빈공간이되었다고 봅니다.
		}
		arr[j] = pivot; // i == j 인 상태입니다.
		return j;
	}

	/**
	 * heap 정렬을 합니다.
	 * 
	 * @param arr
	 */
	void heapSort(int[] arr) {
		for (int i = (arr.length - 1) / 2; i >= 0; i--)
			heapify(arr, i, arr.length); // 모든 내부 node를 index가 큰 node 부터 root node(arr[0])까지 히프화합니다.
		// arr[0](root node)에는 최대 값이 들어가고 모든 서브트리가 heap 이됩니다.
		for (int j = arr.length - 1; j > 0; j--) {
			swap(arr, 0, j); // 0번 원소와 j번 원소를 교환합니다.
			heapify(arr, 0, j); // [0,j) 구간을 히프화 합니다.
		}
	}

	/**
	 * 배열을 히프화 합니다.
	 * 
	 * @param arr
	 * @param nodeIndex
	 *            히프화할 node 의 번호입니다.
	 * @param heapSize
	 *            완전 이진 트리의 크기입니다.
	 */
	void heapify(int[] arr, int nodeIndex, int heapSize) {
		int ai = arr[nodeIndex];
		while (nodeIndex < heapSize / 2) { // arr[i] 는 leaf 가 아닌경우만 loop 를 순환합니다.
			int j = 2 * nodeIndex + 1; // j는 ai의 좌측 자식 노드의 index 입니다.
			if (j + 1 < heapSize && arr[j + 1] > arr[j])
				++j; // 우측 자식 노드의 key가 더 큰 경우 j를 후증가합니다.
			if (arr[j] <= ai)
				break; // 부모가 자식노드보다 크면 loop 를 종료합니다.
			arr[nodeIndex] = arr[j];
			nodeIndex = j;
		}
		arr[nodeIndex] = ai;
	}

	void countingSort(int[] arr) {
		int n = arr.length;

		int output[] = new int[n]; // 결과를 저장할 임시 배열

		int count[] = new int[256]; // arr 에 정수 입력값중에 255 를 넘어가는 건 정렬되지 않습니다...

		for (int i = 0; i < n; ++i)
			++count[arr[i]]; // 계수 를 측정합니다.

		for (int i = 1; i < 256; ++i) // 앞의 원소를 더하여 누적시킵니다.
			count[i] += count[i - 1];

		for (int i = n - 1; i >= 0; i--) {
			output[count[arr[i]] - 1] = arr[i]; // output 배열에 실제 정렬을 수행합니다.
			--count[arr[i]];
		}

		for (int i = 0; i < n; ++i)
			arr[i] = output[i]; // 원본 배열에 결과를 저장
	}

	void radixSort(int[] arr) {
		int[] result = arr ;
		for (int place = 1; place <= 1000000000; place *= 10) {
			result = countingSort(result, place);
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] = result[i]; 
		}
	}

	int[] countingSort(int[] input, int place) {
		int[] out = new int[input.length];

		int[] count = new int[10];

		for (int i = 0; i < input.length; i++) {
			int digit = getDigit(input[i], place);
			count[digit] += 1;
		}

		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}

		for (int i = input.length - 1; i >= 0; i--) {
			int digit = getDigit(input[i], place);

			out[count[digit] - 1] = input[i];
			count[digit]--;
		}

		return out;

	}

	int getDigit(int value, int digitPlace) {
		return ((value / digitPlace) % 10);
	}
}
