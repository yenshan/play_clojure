;;
;; https://www.hackerrank.com/challenges/caesar-cipher-1/problem
;;
(ns hackerrank.algorithms.string.caesar-cipher)

(defn rotate-char [c k]
  (if (not (Character/isLetter c))
    c
    (let [base-d (int (if (Character/isUpperCase c)
                        \A \a))
          offset (- (int c) base-d)]
      (char (+ base-d (mod (+ offset k) 26))))))

(let [_ (read-line)
      string (read-line)
      k (Integer/parseInt (read-line))
      ]
  (->> (map #(rotate-char % k) string)
       (apply str)
       println))

