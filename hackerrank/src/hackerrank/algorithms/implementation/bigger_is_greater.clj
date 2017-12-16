(ns hackerrank.algorithms.implementation.bigger-is-greater)

(defn make-exch-info [a b]
  {(first b) (second a)
   (first a) (second b)})

(defn next-bigger-string [string]
  (let [st-col (map-indexed vector string)
        st-array (to-array st-col)
        len (count st-array)
        exc-pair (loop [i (dec len)]
               (if (< i 0)
                 nil
                 (let [sd (get st-array i)
                       t (for [ti (range (inc i) len)
                               :let [td (get st-array ti)]
                               :when (< (compare (second sd) (second td)) 0)]
                           td)]
                   (if (empty? t)
                     (recur (dec i))
                     (let [std (sort-by second t)]
                       [sd (first std)])))))
        ]
    (when exc-pair
      (let [rep-tgt-i (first (first exc-pair))
            exc-info (make-exch-info (first exc-pair) (second exc-pair))
            n-stcol (map (fn [d]
                           (let [ei (exc-info (first d))]
                             (if ei
                               [(first d) ei]
                               d)))
                         st-col)
            front-chars (filter #(<= (first %) rep-tgt-i) n-stcol)
            rear-chars (filter #(> (first %) rep-tgt-i) n-stcol)
            rear-sorted (sort-by second rear-chars)
            answer (->> (concat front-chars rear-sorted)
                        (map second) 
                        (apply str))

            ]
        answer))))

(let [n (Integer/parseInt (read-line))]
  (doseq [_ (range n)]
    (let [string (read-line)
          answer (next-bigger-string string)]
      (if (nil? answer)
        (println "no answer")
        (println answer)))))

