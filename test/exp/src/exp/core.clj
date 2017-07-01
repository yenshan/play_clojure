(ns exp.core
  (:gen-class))

(defn exp [base e]
  (cond 
    (zero? e) 1
    :else (* base (exp base (dec e)))))

(defn iter-exp
  ([base e] (iter-exp base e 1))
  ([base e res]
   (cond
     (zero? e) res
     :else (recur base (dec e) (* base res)))))

(defn fast-exp
  ([base e] (fast-exp base e 1N))
  ([base e res]
   (cond
     (zero? e) res
     (even? e) (recur (* base base) (/ e 2) res)
     :else (recur base (dec e) (* base res)))))

(defn expmod [base e m]
  (cond
    (zero? e) 1
    :else (-> (expmod base (dec e) m)
              (* base)
              (mod m))))

(defn -main
  [& args]
  (println (exp 3 3))
  (println (iter-exp 4 4))
  (println (fast-exp 5 10)))
