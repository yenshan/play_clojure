;;
;; https://www.hackerrank.com/challenges/two-characters/problem
;;
(ns hackerrank.algorithms.implementation.two-characters)

;;
;; Good problem. This problem is easy but fun.
;; I like this problem because it can be solved by using clean functional programming.
;;

(defn get-uniq-char-list [coll]
  (keys 
    (reduce (fn [res c]
              (if (get res c)
                res
                (assoc res c 1)))
            {}
            coll)))

(defn char-combinations [coll]
  (apply concat
         (loop [[a & rst] coll, res []]
           (if (empty? rst)
             res
             (recur rst
                    (conj res (for [e rst]
                                [a e])))))))

(defn same-char-subseq? [coll]
  (loop [[a & rst] coll, prev-c nil]
    (if (empty? rst)
      (= a prev-c)
      (if (= a prev-c)
        true
        (recur rst a)))))

(let [_ (read-line)
      string (read-line)
      char-lst (get-uniq-char-list string)
      char-combs (char-combinations char-lst)
      t-str-cands (map (fn [c]
                           (let [remain-chars (set c)]
                             (filter #(remain-chars %) string)))
                         char-combs)
      t-combs (filter #(not (same-char-subseq? %)) t-str-cands)
      max-n-t (->> (map count t-combs)
                   (sort #(compare %2 %1))
                   first)
      ]
  (if max-n-t
    (println max-n-t)
    (println 0))
  )
