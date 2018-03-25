;;
;; https://www.hackerrank.com/challenges/prison-transport/problem
;;
(ns hackerrank.functional-programming.functional-structures.prison-transport
  (:require [clojure.string :as s]))


(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn buscost [n]
  (loop [c 1]
    (if (>= (* c c) n)
      c
      (recur (inc c)))))

(defn node [n] {:num n :cnct [] :grp 0})

(defn update-grpno [node no]
  (update node :grp (fn [_] no)))

(defn add-cnct [node cnct]
  (update node :cnct #(conj % cnct)))

(defn update-me-and-cncts [nm me no])

(defn update-cncts [nm cncts no]
  (reduce (fn [res d]
            (let [node (get res d)]
              (if (zero? (:grp node))
                (update-me-and-cncts res d no)
                res)))
          nm
          cncts))

(defn check-cncts [nm cncts]
  (loop [[a & rst] cncts]
    (if (nil? a)
      0
      (let [na (get nm a)]
        (if (zero? (:grp na))
          (recur rst)
          (:grp na))))))

(defn update-me-and-cncts [nm me no]
  (-> nm
      (update me #(update-grpno % no))
      (update-cncts (:cnct (get nm me)) no)))

(defn mark-grpno [nm no]
  (let [node (get nm no)
        cnct-no (check-cncts nm (:cnct node))
        ]
    (cond (not= 0 (:grp node)) nm
          (not= 0 cnct-no) (update-me-and-cncts nm no cnct-no)
          :else (update-me-and-cncts nm no no))))

(let [n (Integer/parseInt (read-line))
      npair (Integer/parseInt (read-line))
      pairs (for [_ (range npair)] (str->nums (read-line)))
      node-m (reduce (fn [res d]
                       (assoc res d (node d)))
                     {}
                     (range 1 (inc n)))
      node-m2 (reduce (fn [res [a b :as dat]]
                        (-> res
                            (update a #(add-cnct % b))
                            (update b #(add-cnct % a))))
                      node-m
                      pairs)
      node-m3 (reduce mark-grpno node-m2 (keys node-m2))
      grps (map :grp (vals node-m3))
      grp-cnt (reduce (fn [res d]
                        (if (get res d)
                          (update res d inc)
                          (assoc res d 1)))
                      {}
                      (map :grp (vals node-m3)))
      cost (reduce + (map #(buscost %) (vals grp-cnt)))
      ]
  (println cost)
  )

