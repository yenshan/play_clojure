(ns hackerrank.algorithms.implementation.bigger-is-greater)

(defn drop-nth [n coll]
  (->> (map-indexed vector coll)
       (remove (fn [[i _]] (= i n )))
       (map second)))

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
              front-coll (take pivot st-seq)
              rear-coll (cons td 
                              (sort (cons piv-c (drop piv-next (drop-nth ti st-seq)))))
              answer (apply str (concat front-coll rear-coll))
              ]
          answer
          ))))

(let [n (Integer/parseInt (read-line))
      answers (for [_ (range n)] 
                (next-bigger-string (read-line)))]
  (doseq [a answers]
    (if (nil? a)
      (println "no answer")
      (println a))))

