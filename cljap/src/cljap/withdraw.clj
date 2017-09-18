(ns cljap.withdraw)

(defn make-withdraw [initial]
  (let [balance (atom initial)]
    (fn [amount]
      (if (>= @balance amount)
        (reset! balance (- @balance amount))
        "Insufficient funds"))))
   
(defn make-account [initial]
  (let [balance (atom initial)]
    (letfn [(withdraw [amount]
              (if (>= @balance amount)
                (reset! balance (- @balance amount))
                "Insufficient funds"))
            (deposit [amount]
              (reset! balance (+ @balance amount)))
            (dispatch [m]
              (condp = m
                'withdraw withdraw
                'deposit deposit
                :else (assert "Unknown request: MAKE-ACCOUNT" m)))]
      dispatch)))

