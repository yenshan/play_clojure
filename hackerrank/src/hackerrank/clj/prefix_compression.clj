(ns hackerrank.clj.prefix-compression)

(defn get-prefix 
  ([str1 str2] (get-prefix str1 str2 []))
  ([str1 str2 res]
   (if (or (empty? str1) (empty? str2)
           (not= (first str1) (first str2)))
     (map #(apply str %) (list res str1 str2))
     (recur (rest str1) (rest str2) (conj res (first str1))))))
     
(doseq [i (get-prefix (read-line) (read-line))]
  (println (count i) i))

