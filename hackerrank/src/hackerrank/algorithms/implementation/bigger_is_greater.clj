;;
;; https://www.hackerrank.com/challenges/bigger-is-greater/problem
;;
(ns hackerrank.algorithms.implementation.bigger-is-greater)

;;
;; It was hard to pass all test cases. Performance is the bottleneck.
;; I improved performace little by little.
;;

(defn drop-nth [n coll]
  (concat (subvec coll 0 n)
          (subvec coll (inc n))))

(defn next-bigger-string [^String string]
  (let [st-map (to-array string)
        len (.length string)
        pivot (loop [i (- len 2), prev (last string)]
                (if (< i 0 )
                  nil
                  (let [c (get st-map i)]
                    (if (< (compare c prev) 0)
                      i
                      (recur (dec i) c)))))
        ]
    (when pivot 
        (let [piv-c (get st-map pivot)
              piv-next (inc pivot)
              [ti td] (reduce (fn [res i]
                                (let [c (get st-map i)
                                      [_ pd] res]
                                  (if (and (< (compare piv-c c) 0)
                                           (< (compare c pd) 0))
                                    [i c]
                                    res)))
                              [piv-next (get st-map piv-next)]
                              (range piv-next len))
              st-seq (vec string) 
              front-coll (conj (subvec st-seq 0 pivot) td)
              rear-coll (sort (cons piv-c (drop piv-next (drop-nth ti st-seq))))
              ]
              (apply str (concat front-coll rear-coll))
          ))))

(let [n (Integer/parseInt (read-line))
      inputs (for [_ (range n)] (read-line))
      answers (map next-bigger-string inputs)] 
  (doseq [a answers]
    (if (nil? a)
      (println "no answer")
      (println a))))

