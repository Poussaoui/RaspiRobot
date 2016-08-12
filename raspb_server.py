# chat_server.py

import RPi.GPIO as GPIO
from xml.etree import ElementTree
from xml.dom import minidom
from timeit import default_timer as timer
from threading import Thread
import sys, socket, select,threading ,time ,os

HOST = '' 
SOCKET_LIST = []
RECV_BUFFER = 1024 
PORT = 9008
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)
GPIO.setup(7,GPIO.OUT)
GPIO.setup(11,GPIO.OUT)
GPIO.setup(13,GPIO.OUT)
GPIO.setup(15,GPIO.OUT)

#define the pin that goes to the circuit
pin_to_circuit_f = 12
pin_to_circuit_b = 23
pin_to_circuit_l = 21
pin_to_circuit_r = 22

#GPIO.setmode(GPIO.BCM)
# Define GPIO to use on Pi
GPIO_TRIGGER = 19
GPIO_ECHO    = 26

GPIO.setup(GPIO_TRIGGER,GPIO.OUT)  # Trigger
GPIO.setup(GPIO_ECHO,GPIO.IN)      # Echo
GPIO.output(GPIO_TRIGGER, False)

root = minidom.Document()
xml = root.createElement('root')
root.appendChild(xml)


def view_path():
    file_name = 'path.xml'
    full_name = os.path.abspath(os.path.join(file_name))
    dom = ElementTree.parse(full_name)
    coursses = dom.findall('path')
    list = []
    for c in coursses:
        num = c.find('num').text
        direction = c.find('direction').text
	list.append(num)
        print('* {} - {}'.format(num, direction))
	
    print "List : ",list
    list.reverse();
    print "List : ", list
    
    for item in list:
	print item
	if item == '1':
            forward(0.5,0)
        elif item == '2':
            reverse(0.5,0)
        elif item == '4':
            turn_left(0.5,0)
        elif item == '3':
            turn_right(0.5,0)
        elif item == '5':
            turn_back_left(0.5,0)
        elif item == '6':
            turn_back_right(0.5,0)

def record_path(_id,_direc):

    path = root.createElement('path')
    path.setAttribute('id', str(_id))
    xml.appendChild(path)
    num = root.createElement('num')
    num.appendChild(root.createTextNode(str(_id)))
    path.appendChild(num)
    direction = root.createElement('direction')
    direction.appendChild(root.createTextNode(str(_direc)))
    path.appendChild(direction)
    xml_str = root.toprettyxml(indent="\t")
    save_path_file = "path.xml"
    with open(save_path_file, "w") as f:
        f.write(xml_str)
        
def measure():
  GPIO.output(GPIO_TRIGGER, True)
  time.sleep(0.00001)
  GPIO.output(GPIO_TRIGGER, False)
  start = time.time()
  
  while GPIO.input(GPIO_ECHO)==0:
    start = time.time()

  while GPIO.input(GPIO_ECHO)==1:
    stop = time.time()

  elapsed = stop-start
  distance = (elapsed * 34300)/2

  return distance

def measure_average():
  distance1=measure()
  time.sleep(0.1)
  distance2=measure()
  time.sleep(0.1)
  distance3=measure()
  distance = distance1 + distance2 + distance3
  distance = distance / 3
  return distance

def rc_time (pin_to_circuit):
    count = 0
  
    #Output on the pin for 
    GPIO.setup(pin_to_circuit, GPIO.OUT)
    GPIO.output(pin_to_circuit, GPIO.LOW)
    time.sleep(0.1)

    #Change the pin back to input
    GPIO.setup(pin_to_circuit, GPIO.IN)
  
    #Count until the pin goes high
    while (GPIO.input(pin_to_circuit) == GPIO.LOW):
        count += 1

    return count

def forward(tf,t):
    #init()
    GPIO.output(11,True)
    GPIO.output(13,True)
    time.sleep(tf)
    GPIO.output(11,False)
    GPIO.output(13,False)
    time.sleep(tf)
    #GPIO.cleanup()
	if t == 1:
        record_path(1,forward.func_name)
 
def reverse(tf,t):
    #init()
    GPIO.output(7,True)
    GPIO.output(15,True)
    time.sleep(tf)
    GPIO.output(7,False)
    GPIO.output(15,False)
    time.sleep(tf)
    #GPIO.cleanup()
	if t == 1:
        record_path(1,forward.func_name)
    
def turn_left(tf,t):
    #init()
    GPIO.output(11,True)
    time.sleep(tf)
    GPIO.output(11,False)
    GPIO.output(13,False) 
    time.sleep(tf)  
    #GPIO.cleanup()
	if t == 1:
        record_path(1,forward.func_name)
def turn_right(tf,t):
    #init()
    GPIO.output(13,True)
    time.sleep(tf)
    GPIO.output(11,False)
    GPIO.output(13,False)
    time.sleep(tf)
    #GPIO.cleanup()
	if t == 1:
        record_path(1,forward.func_name)
    

def turn_back_left(tf,t):
    init()
    GPIO.output(15,True)
    time.sleep(tf)
    GPIO.output(15,False)
    GPIO.output(7,False)
    time.sleep(tf)  
    GPIO.cleanup()

def turn_back_right(tf):
    init()
    GPIO.output(7,True)
    time.sleep(tf)
    GPIO.output(7,False)
    GPIO.output(15,False)
    time.sleep(tf)
    GPIO.cleanup()

def tourn():
    forward(0.5,0)
    turn_back_right(0.75,0)
    turn_right(0.77,0)
	
def timer(name,delay,repeat):
    print name+"Started"
    while repeat > 0:
        time.sleep(delay)
        print "xxxx"
        repeat -= 1
    print name+"Completed"        
    

def lightOn(name,delay,repeat):
    print name+"Started"
    try:
        while repeat > 0:
            time.sleep(delay)
            print "lightOn"
            if rc_time(pin_to_circuit_f)<70:
                forward(0.3,0)
	        print "Forward"
		print rc_time(pin_to_circuit_f)
                
	    elif rc_time(pin_to_circuit_b)<70:
                reverse(0.3,0)
                print "Backward"
                print rc_time(pin_to_circuit_b)

	    elif rc_time(pin_to_circuit_l)<70:
                turn_left(0.5,0)
                print "Lift"
                print rc_time(pin_to_circuit_l)
                
	    elif rc_time(pin_to_circuit_r)<70:
                turn_right(0.5,0)
                print "Right"
                print rc_time(pin_to_circuit_r)
            repeat -= 1
    except KeyboardInterrupt:
        pass
    finally:
        #GPIO.cleanup()
        print name+"Completed"
    
def obstOn(name,delay,repeat):
    print name+"Started"
    try:
        while repeat > 0:
            time.sleep(delay)
            distance = measure_average()
            if distance < 8:
                print "Distance : %.1f" % distance
                reverse(0.5)                
            time.sleep(0.5)
            print "obstOn"
            repeat -= 1
    except KeyboardInterrupt:
        #GPIO.cleanup()
        print name+"Completed"           
    
def reception(data):
    #print data
	"""
    if data == "Goback":
        t = Thread(target=timer, args=("Time1",1,5))
        t.start()
	"""
    if data == "lightOn":
        t = Thread(target=lightOn, args=("Time1",1,10))
        t.start()
    elif data == "obstOn":
        t = Thread(target=obstOn, args=("Time1",1,10))
        t.start()
    elif data == "forward":
        print "forward"
        forward(0.5,0)
    elif data == "backward":
        print "backward"
        reverse(0.5,0)
    elif data == "left":
        print "left"
        turn_left(0.5,0)
    elif data == "right":
        print "right"
        turn_right(0.5,0)
		
	elif data == "forwardB":
        print "forward"
        forward(0.5,1)
    elif data == "backwardB":
        print "backward"
        reverse(0.5,1)
    elif data == "leftB":
        print "left"
        turn_left(0.5,0)
    elif data == "rightB":
        print "right"
        turn_right(0.5,1)
        
def raspb_server():

    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server_socket.bind((HOST, PORT))
    server_socket.listen(10)
 
    # add server socket object to the list of readable connections
    SOCKET_LIST.append(server_socket)
 
    print "Raspberry Server started on port " + str(PORT)
 
    while 1:

        # get the list sockets which are ready to be read through select
        # 4th arg, time_out  = 0 : poll and never block
        ready_to_read,ready_to_write,in_error = select.select(SOCKET_LIST,[],[],0)
      
        for sock in ready_to_read:
            # a new connection request recieved
            if sock == server_socket: 
                sockfd, addr = server_socket.accept()
                SOCKET_LIST.append(sockfd)
                 
            # a message from a client, not a new connection
            else:
                # process data recieved from client, 
                try:
                    # receiving data from the socket.
                    data = sock.recv(RECV_BUFFER)
                    
                    if data:
                        reception(data)
                # exception 
                except:
                    continue

    server_socket.close()

 
if __name__ == "__main__":

    sys.exit(raspb_server())


         
