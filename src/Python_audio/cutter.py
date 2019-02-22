from pydub import AudioSegment
import sys

def func(file_name,save_name,start_time,stop_time):

    sound = AudioSegment.from_mp3(file_name)
    #notice:mp3??

    print ("time:",start_time,"~",stop_time)
    word = sound[start_time:stop_time]

    print (save_name+"  done")

    word.export(save_name, format="wav")
    return 0;
    

a = []
a.append(sys.argv[1])
a.append(sys.argv[2])
a.append(int(sys.argv[3]))
a.append(int(sys.argv[4]))

print(func(a[0],a[1],a[2],a[3]))
