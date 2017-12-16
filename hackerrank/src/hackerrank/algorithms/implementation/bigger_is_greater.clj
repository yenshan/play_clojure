(ns hackerrank.algorithms.implementation.bigger-is-greater)

(defn make-exch-info [a b]
  {(first b) (second a)
   (first a) (second b)})

(defn drop-nth [n coll]
  (->> (map-indexed vector coll)
       (remove (fn [[i _]] (= i n )))
       (map second)))

(defn next-bigger-string [string]
  (let [st-col (map-indexed vector string)
        st-map (reduce (fn [res [i c]]
                         (assoc res i c))
                       {}
                       st-col)
        len (count string)
        pivot (loop [i (- (count string) 2), prev (last string)]
                (if (< i 0 )
                  nil
                  (let [c (get st-map i)]
                    (if (< (compare c prev) 0)
                      i
                      (recur (dec i) c)))))
        ]
    (when pivot 
        (let [piv-c (get st-map pivot)
              td (loop [i (inc pivot), res nil]
                   (if (= i len)
                     res
                     (let [c (get st-map i)]
                       (if (< (compare piv-c c) 0)
                         (if (or (nil? res) (< (compare c (second res)))) (recur (inc i) [i c])
                           (recur (inc i) res))
                         (recur (inc i) res)))))
              exc-pair [[pivot piv-c] td]
              ]
          (when exc-pair
            (let [[[si sd] [ti td]] exc-pair
                  front-coll (concat (take si string) (list td))
                  rear-coll (sort (cons sd (drop (inc si) (drop-nth ti string))))
                  answer (apply str (concat front-coll rear-coll))
                  ]
              answer
              ))
          ))))

(let [n (Integer/parseInt (read-line))
      answers (for [_ (range n)] 
                (next-bigger-string (read-line)))]
  (doseq [a answers]
    (if (nil? a)
      (println "no answer")
      (println a))))

