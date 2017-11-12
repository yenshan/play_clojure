(ns hackerrank.algorithms.implementation.between-two-sets)

(defn gcd [a b]
  (let [r (mod a b)]
    (if (zero? r)
      b
      (gcd b r))))

(defn scm [big small]
  (loop [tb big, ib 1, 
         ts small, is 2]
    ;(println tb ib ts is)
    (let [nbg (* tb ib)
          nsm (* ts is)]
      (cond (= nbg nsm) nbg
            (> nbg nsm) (recur tb ib ts (inc is))
            :else (recur ts is tb (inc ib))
            ))))

(def dat [16 32 96])

(defn do-ff [f coll]
  (->> (for [a coll, b coll] [a b])
       (filter (fn [[a b]] (not= a b)))
       (reduce (fn [res [a b :as d]]
                 (conj res (sort #(compare %2 %1) d)))
               #{})
       (reduce (fn [res [a b]]
                 (conj res (f a b)))
               #{})
       ))

(defn gcd-coll [coll]
  (->> (do-ff gcd coll)
       sort
       first))

(defn scm-coll [coll]
  (->> (do-ff scm coll)
       (sort #(compare %2 %1))
       first))


(def dat1 [2 4])

(scm-coll [2 4 8])
(gcd-coll [16 32 96])

(let [num-gcd (gcd-coll dat)]
  (map #(/ num-gcd %) dat1))
  
        
