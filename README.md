# RaspiRobot
The Raspberry Pi Robot - Client (Android) / Server (Python)
Démo : https://youtu.be/DegDe85UST8
Simulation app on play store:
https://play.google.com/store/apps/details?id=com.inc.poussaoui.raspirobot

I.	Présentation : 
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


L.	Schéma:

![image](https://cloud.githubusercontent.com/assets/19296142/17638961/1c953f62-60e6-11e6-920d-55e5e90dd9c7.png)


![image](https://cloud.githubusercontent.com/assets/19296142/17638962/28fccd2e-60e6-11e6-91c7-c3e241d5835d.png)


2.	LES SOLUTIONS LOGICIELLES : 

a.	Technologies  
 
o	Android 
Android, prononcé androïde, est un système d'exploitation mobile open source  basé sur le noyau Linux et développé actuellement par Google. Le système a été aborder pour les smartphones et tablettes tactiles. 
 
o	XML : 
 
XML (entendez eXtensible Markup Language et traduisez Langage à balises étendu, ou Langage à balises extensible) est en quelque sorte un langage HTML amélioré permettant de définir de nouvelles balises. Ils'agit effectivement d'un langage permettant de mettre en forme des documents grâce à des balises (markup). 
 
Il est un sous ensemble de SGML (Standard Generalized Markup 
Language), défini par le standard ISO8879 en 1986, utilisé dans le milieu de la Gestion Electronique Documentaire (GED). XML reprend la majeure partie des fonctionnalités de SGML, il s'agit donc d'une simplification de SGML afin de le rendre utilisable sur le web 
 
o	Python : 
Python est un langage de programmation objet, multiparadigme et multiplateformes. Il favorise la programmation impérative structurée, fonctionnelle et orientée objet. Il est doté d'un typage dynamique fort, d'une gestion automatique de la mémoire par ramassemiettes et d'un système de gestion d'exceptions ; il est ainsi similaire à Perl, Ruby, Scheme, Smalltalk et Tcl. 
Le langage Python est placé sous une licence libre proche de la licence BSD3 et fonctionne sur la plupart des plates-formes informatiques, des supercalculateurs aux ordinateurs centraux4, de Windows à Unix en passant par GNU/Linux, Mac OS, ou encore Android, iOS, et aussi avec Java ou encore .NET. Il est conçu pour optimiser la productivité des programmeurs en offrant des outils de haut niveau et une syntaxe simple à utiliser. 


1. Communication Android – Raspberry 
 
Cette partie est réalisée par l’équipe “communication”, dans un premier temps, elle a pour but de choisir le support pour la communication entre Android – Raspberry télécommandée et les protocoles entre ces deux derniers. 
b.	Choix du support 
 
o	Wifi 
 
Le Wi-Fiest un ensemble de protocoles de communication sans fil régis par les normes du groupe IEEE 802.11 (ISO/CEI 8802-11). Un réseau Wi-Fi permet de relier par ondes radio 
plusieurs appareilsinformatiques (ordinateur, routeur, smartphone, décodeur Internet, etc.) au sein d'un réseau informatique afin de permettre la transmission de données entre eux. 
 
o	Avantages du Wifi: 
Mobilité  
 
La connexion au réseau sans fil permet de se déplacer librement dans le rayon disponible. On peut ainsi emmener son laptop de la salle de réunion à l’atelier sans avoir à brancher/débrancher quoi que ce soit.   
Facilité  
 
Un réseau WiFi bien configuré permet de se connecter très facilement, à condition, biensûr, de posséder une autorisation. Il suffit généralement de se trouver dans la zone de couverture pour être connecté.  
 
Souplesse  
 
La souplesse d’installation du WiFi permet d’adapter facilement la zone d’action en fonction des besoins. Si le point d’accès est trop faible, on ajoute des répéteurs pour étendre la couverture.  
 
Coût  
 
La plupart des éléments du réseau WiFi (point d’accès, répéteurs, antennes…) peuvent être simplement posés. L’installation peut donc parfois se faire sans le moindre outillage, ce qui réduit les coûts de main-d’œuvre. Le budget de fonctionnement est similaire à un réseau filaire.  
 
Evolutivité  
 
La facilité d’extension ou de restriction du réseau permet d’avoir toujours une couverture WiFi correspondant aux besoins réels. 

![image](https://cloud.githubusercontent.com/assets/19296142/17639035/cade9230-60e6-11e6-94cb-b79abfbad61d.png)

![image](https://cloud.githubusercontent.com/assets/19296142/17639029/c114cfd0-60e6-11e6-891a-9b7dcf5ae72c.png)
