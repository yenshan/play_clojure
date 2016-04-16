(ns pong.core
  (:import [javax.swing JFrame JPanel Timer]
           [java.awt.event ActionListener])
  (:gen-class))

(defn new-ball
  ([x y w h vx vy] {:x x :y y :w w :h h :vx vx :vy vy})
  ([bal x y vx vy] (assoc bal :x x :y y :vx vx :vy vy)))

(defn new-bar [x y w h] {:x x :y y :w w :h h})

(def ball (atom (new-ball 0 0 10 10 1 1)))
(def bar (atom (new-bar 200 300 50 10)))

(defn nextv
  [v pos rng]
  (if (and (<= pos (- rng 10))
           (>= pos 0))
    v
    (- v)))

(defn next-ball
  [bal max-x max-y]
  (let [vx (:vx @ball) 
        vy (:vy @ball)
        x (+ (:x @ball) vx)
        y (+ (:y @ball) vy)]
        (new-ball x y 
                  (:w @ball) (:h @ball)
                  (nextv vx x max-x) (nextv vy y max-y))))

(defn draw-ball
  [bal g]
  (let [x (:x bal) y (:y bal)
        w (:w bal) h (:h bal)]
    (doto g 
      (.setColor java.awt.Color/BLACK)
      (.fillOval x y w h))))

(defn draw-bar
  [bar g]
  (let [x (:x bar) y (:y bar)
        w (:w bar) h (:h bar)]
    (doto g 
      (.setColor java.awt.Color/BLACK)
      (.fillRect x y w h))))

(def panel 
  (proxy [JPanel ActionListener] []
    (paintComponent [g]
      (let [w (proxy-super getWidth)
            h (proxy-super getHeight)]
        (reset! ball (next-ball @ball w h))
        (draw-ball @ball g)
        (draw-bar @bar g)
        ))
    (actionPerformed [e]
      (.repaint this))))

(defn -main
  [& args]
  (let [frame (JFrame. "Test Paint")
        timer (Timer. 10 panel)]
    (doto frame
      (.add panel)
      (.pack)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setSize 400 400)
      (.setVisible true))
    (.start timer)))

