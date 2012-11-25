(ns todo.core
  (:use
    [compojure.core :as compojure :only [ANY]]
    [liberator.core :only [defresource]]))

(defresource home
  :handle-ok "welcome home!"
  :available-media-types ["text/plain"])

(compojure.core/defroutes handler
  (ANY "/" [] home))
