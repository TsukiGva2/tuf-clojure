(ns tuf.core
  (:gen-class))

(defn lifo-into
  "put a vector at the beginning of another vector"
  [target vect]
  (flatten (cons vect target)))

(def PARSE-ERR 1)

(defn math-operation
  [operator]
  (fn [stack]
    (list
          (apply operator
                    (map #(Integer. %) (take 2 stack))))))

(def functions
  {"emit" (fn [stack] (println (first stack)))
   "pop"  (fn [stack] (drop 1 stack))
   "rot"  (fn [stack] (reverse (take 3 stack)))
   "swap" (fn [stack] (reverse (take 2 stack)))
   "add"  (math-operation +)
   "sub"  (math-operation -)})

(defn tufparse
  [expr]
  (let [spexpr (clojure.string/split expr #" ")
        funcs (loop [[curr & remn] spexpr
               result []]
          (if (and (empty? remn) (nil? curr))
            (if (empty? result) PARSE-ERR result)
            (recur
             remn
             (conj result
                   (get functions curr curr)))))]
    (map (fn [func]
           (if (string? func)
             (if (re-matches #"\".*\"" func)
               (fn [stack]
                 (lifo-into stack
                  (nth
                   (re-matches #"\"(.*)\"" func) 1)))
               nil)
             func))
         funcs)))

(defn tufval
  [expr state]
  (let [funcs (filter
         (complement nil?)
         (tufparse expr))]
    (loop [[curr & remn] funcs
           stack (:stack state)]
      (if (and (empty? remn) (nil? curr))
        (assoc state :stack stack)
        (recur remn (curr stack))))))

(defn -main
  "tuf repl"
  [& args]
  (loop [state {:stack '()}]
    (println "stack:" (:stack state))
    (print "<tuf->")
    (flush)
    (recur (tufval (read-line) state))))
