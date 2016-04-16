(ns pong.core
  (:import [javax.swing JFrame JPanel Timer]
           [java.awt.event ActionListener KeyListener])
  (:gen-class))

(defn new-ball
  ([x y w h vx vy] {:x x :y y :w w :h h :vx vx :vy vy}))

(defn new-bar [x y w h] {:x x :y y :w w :h h})

(def ball (atom (new-ball 0 0 10 10 1 1)))
(def bar (atom (new-bar 200 300 50 10)))

(defn move-left
  [bar]
  (reset! bar (assoc @bar
                     :x (- (:x @bar) 5))))
(defn move-right
  [bar]
  (reset! bar (assoc @bar
                     :x (+ (:x @bar) 5))))

(defn nextv
  [v pos rng]
  (if (and (<= pos (- rng 10))
           (>= pos 0))
    v
    (- v)))

(defn next-ball
  [bal max-x max-y]
  (let [vx (:vx bal) 
        vy (:vy bal)
        x (+ (:x bal) vx)
        y (+ (:y bal) vy)]
        (new-ball x y 
                  (:w bal) (:h bal)
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

(defn main-panel 
  []
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

(def klistn
  (proxy [KeyListener] []
    (keyPressed [e]
      (let [kc (.getKeyCode e)]
        (cond (= kc 37) (move-left bar) 
              (= kc 39) (move-right bar))))
    (keyReleased [e])
    (keyTyped [e])))

(defn -main
  [& args]
  (let [panel (main-panel) 
        frame (JFrame. "Test Paint")
        timer (Timer. 10 panel)]
    (doto panel
      (.setFocusable true)
      (.addKeyListener klistn))
    (doto frame
      (.add panel)
      (.pack)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setSize 400 400)
      (.setVisible true))
    (.start timer)))

