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
        exc-pair (loop [i (dec len)]
                   (if (< i 0)
                     nil
                     (let [sd (st-map i)
                           t (loop [ti (inc i), res nil]
                               (if (= ti len)
                                 res
                                 (let [td (st-map ti)]
                                   (if (< (compare sd td) 0)
                                     (cond (nil? res) (recur (inc ti) [ti td])
                                           (< (compare td (second res)) 0) (recur (inc ti) [ti td])
                                           :else (recur (inc ti) res))
                                     (recur (inc ti) res)))))
                           ]
                       (if (nil? t)
                         (recur (dec i))
                         [[i sd] t]))))
        ]
    (when exc-pair
      (let [[[si sd] [ti td]] exc-pair
            front-coll (concat (take si string) (list td))
            rear-coll (sort (cons sd (drop (inc si) (drop-nth ti string))))
            answer (apply str (concat front-coll rear-coll))
            ]
        answer
      ))
    ))

(let [n (Integer/parseInt (read-line))]
  (doseq [_ (range n)]
    (let [string (read-line)
          answer (next-bigger-string string)
          ]
      (if (nil? answer)
        (println "no answer")
        (println answer)))))

