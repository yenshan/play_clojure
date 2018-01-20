;;
;; https://www.hackerrank.com/challenges/separate-the-numbers/problem
;;
(ns hackerrank.algorithms.string.separete-the-numbers)

(defn is-seq-num? [nstr string]
  (if (empty? string)
    true
    (let [next-nstr (str (inc (read-string nstr)))
          i-nstr (.indexOf string next-nstr)]
      (if (= 0 i-nstr)
        (recur next-nstr (subs string (count next-nstr)))
        false))))

(let [n (Integer/parseInt (read-line))]
  (doseq [_ (range n)]
    (let [s (read-line)]
      (loop [len 1]
        (if (> len (/ (count s) 2))
          (println "NO")
          (let [base (subs s 0 len)
                rst (subs s len)
                ]
            (if (is-seq-num? base rst)
              (println "YES" base)
              (recur (inc len)))))))))

