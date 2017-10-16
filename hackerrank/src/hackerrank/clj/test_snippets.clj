(ns hackerrank.clj.test-snippets)

(def test-dat1 [1 2 3 4])
(def test-dat2 [\a \b \c \d])

(defn all-patterns 
  ([coll1 coll2] (all-patterns coll1 coll2 []))
  ([coll1 coll2 res]
   (if (empty? coll1)
     res
     (let [r (for [e coll1]
               (all-patterns (remove #(= % e) coll1) 
                             (rest coll2)
                             (conj res [e (first coll2)])))]
       (if (= (count coll1) 1)
         r
         (apply concat r))))))

(all-patterns test-dat1 test-dat2)

