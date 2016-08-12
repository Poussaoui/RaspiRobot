# RaspiRobot
The Raspberry Pi Robot - Client (Android) / Server (Python)


  Ce travail réalisé dans le cadre du projet de fin de module.  
Ce rapport présente le processus de conception et la réalisation d'un projet dans lequel on vaut utiliser une nouvelle technologie  “Raspberry” développé par le langage Python. 
 
 
II.	Présentation du sujet : 
Nous allons réalisé un robot avec Raspberry pi controller à distance via un smartphone. 
 
Ce robot a plusieurs fonctionnalités parmi eux : 
-	Il permet de détécter les obstacles et les éviter ainsi de changer son chemin. 
-	Il permet de mémoriser le chemin qui a suivi en revenant au point de départ vers le même chemin. 
-	Il est capable de suivre un indice “lumière” comme chemin. 
 
 
 
III.	Développement Technique : 
 
1.LES SOLUTIONS MATÉRIELLES : 
 
a.	Raspbeery : 


![image](https://cloud.githubusercontent.com/assets/19296142/17629834/7224f076-60b4-11e6-9aa1-42dcce4b4d51.png)

Le Raspberry Pi est un nano-ordinateur monocarte à processeur ARM conçu par le créateur de jeuxvidéo David Braben, dans le cadre de sa fondation Raspberry Pi. Cet ordinateur, qui a la taille d'une carte de crédit, est destiné à encourager. 
        
b.	Fils Breadboard : 
  
 ![image](https://cloud.githubusercontent.com/assets/19296142/17629875/abb29d0c-60b4-11e6-8aba-6f11493df194.png)

Ce sont des fils que l'on peut utiliser pour relier les composants entre les colonnes. 
 
 
c.	LED : 
 
  
 ![image](https://cloud.githubusercontent.com/assets/19296142/17629888/b8981cf4-60b4-11e6-9afe-82b3302d407b.png)

Une diode électroluminescente( LED) est une source de lumière à semiconducteur à deux fils . Il est une diode à jonction p -n , qui émet de la lumière lorsqu'il est activé. 
 
d.	Breadboard : 
  
 ![image](https://cloud.githubusercontent.com/assets/19296142/17629906/c505a1aa-60b4-11e6-9687-e0205442b04c.png)

La plaque d’essai sans soudure nous permet de réaliser rapidement un montage électronique en insérant les pattes des composants et les fils dans les trous. 
 
e.	PhotoResistor: 
 
![image](https://cloud.githubusercontent.com/assets/19296142/17629915/d0f444ee-60b4-11e6-8749-f57128c23c1c.png)
	 
Dans ce clignotement led circuit, le LDR ou photo résistance est connecté de telle façon que lorsque l'intensité lumineuse varie elle influence la fréquence de clignotement et la luminosité des LED .Pour obtenir des effets intéressants on peut utiliser les LED D1 à D4 . 

f.	USB Wifi Adapter 
 
  ![image](https://cloud.githubusercontent.com/assets/19296142/17629930/e6e9e6dc-60b4-11e6-9527-e9aba1c5fd8e.png)
USB WIFI (802.11 b / g / n) adaptateur pour le Raspberry Pi, facile à utiliser et facile à installer. Les adaptateurs WIFI ont été fabriqués en particulier pour l'ordinateur Raspberry Pi.  
Caractéristiques principales : 
•	2.4 GHz, ISB Band 
•	Low Power (<110mA) avec Advanced Power Management 
•	Interface hôte: USB 2.0 / 1.1 
•	LED: Link / Activity 
 
 
g.	Ultrason HC-SR04 : 
  
  ![image](https://cloud.githubusercontent.com/assets/19296142/17629993/4d7820e4-60b5-11e6-901d-dc411664be37.png)

Le fonctionnement du module est le suivant : 
Il faut envoyer une impulsion niveau haut (à + 5v) pendant au moins 10 µs sur la broche ‘Trig Input’; cela déclenche la mesure. En retour la sortie ‘Output’ ou ‘Echo’, va fournir une impulsion + 5v dont la durée est proportionnelle à la distance si le module détecte un objet. Afin de pouvoir calculer la distance en cm, on utilisera la formule suivante : distance = (durée de l’impulsion (en µs) / 58 
Voici une représentation graphique de la séquence de fonctionnement du module : 
   
   ![image](https://cloud.githubusercontent.com/assets/19296142/17630001/5d64d7ea-60b5-11e6-9272-de6eea2a3bf7.png)
   
h.	Condensateur : 
 
 
 ![image](https://cloud.githubusercontent.com/assets/19296142/17630013/6bd67c5c-60b5-11e6-89a2-6a38a65db54b.png)

Le Condensateur est un composant électronique élémentaire, constitué de deux armatures conductrices en influence totale et séparées par un isolant polarisable (ou « diélectrique »). Sa propriété principale est de pouvoir stocker des charges électriques opposées sur ses armatures. La valeur absolue de ces charges est proportionnelle à la valeur absolue de la tension qui lui est appliquée. Le condensateur est caractérisé par le coefficient de proportionnalité entre charge et tension appelé capacité électrique et exprimée en farads (F). La relation caractéristique d'un condensateur idéal est : 
  
 ![image](https://cloud.githubusercontent.com/assets/19296142/17630026/78634b8a-60b5-11e6-8488-37866651151e.png)

i.	Resistor : 
  
 ![image](https://cloud.githubusercontent.com/assets/19296142/17630039/806d290e-60b5-11e6-8b48-a1b99ef57377.png)
Une résistance est un composant électronique ou électrique dont la principale caractéristique est d'opposer une plus ou moins grande résistance (mesurée en ohms) à la circulation du courant électrique. 
La valeur des résistances à couche standard est habituellement indiquée sur le composant sous forme d'anneaux de couleurs. Le code en est défini : 

 ![image](https://cloud.githubusercontent.com/assets/19296142/17630059/8e113302-60b5-11e6-8cad-bcbfb856037d.png)

  ![image](https://cloud.githubusercontent.com/assets/19296142/17630073/97b3445e-60b5-11e6-9fe8-df435f60172a.png)

j.      Kit chassie 2 roues pour robot :

![image](https://cloud.githubusercontent.com/assets/19296142/17630086/a478ba70-60b5-11e6-8297-966ab97c02f6.png)

Compatible avec Raspberry Idéal pour les amateurs de la robotique 
Kit comprend: 
 
1	x châssis de voitures 
2	x moteur à engrenages 
2 x pneu de voiture 
2 x Speed Encoder 
2 x Fastener 
1 x roue Universal 
1 x Tournevis 
1 x boîte (piles non comprises) 

k.      Controlleur de moteur 
L298N double pont H, Module de commande pour moteur pas à pas et moteur DC 
  
  ![image](https://cloud.githubusercontent.com/assets/19296142/17630192/31f02c26-60b6-11e6-93dc-3b89077c206d.png)


Caractéristique: 
•	Léger, petit 
•	Des capacités hors-pair pour contrôle moteur 
•	Diodes de protections 
•	Un dissipateur (pour dissiper la chaleur en cas de forte charge) 
•	Un sélecteur pour sélectionner la source d'alimentation 
•	4 Sélecteurs pour les résistances pull up 
•	Sortie pour 2 moteurs continu/ 1 moteur pas-à-pas (4 bobines, deux phases) 
•	Indicateur LED pour sens de rotation moteur 
•	Indicateur LED pour alimentation 5V 
•	4 trous de fixation standard 
