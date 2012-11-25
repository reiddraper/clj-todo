(ns todo.list-resource
  (:use
    [compojure.core :as compojure :only [ANY routes]])
  (:require compojure.route
            [liberator.core :as liberator]))

(defn handle-ok
  [req]
  (str "got list " (get-in req [:request :list-id])))

(def resource (liberator/resource :handle-ok handle-ok
                                  :available-media-types ["text/plain"]))
