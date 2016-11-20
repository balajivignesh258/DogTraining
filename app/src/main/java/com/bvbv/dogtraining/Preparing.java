package com.bvbv.dogtraining;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Preparing extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private static boolean snackMessageRead = false;
    String [] displayPoints;
    String heading;
    int currentPage;
    int totalPages;
    AdsManager adsManager;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparing);
        Intent intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String activityToOpen = intent.getStringExtra("activityToOpen");
        ((TextView) findViewById(R.id.a_t_preparing)).setMovementMethod(new ScrollingMovementMethod());
        mDetector = new GestureDetectorCompat(this,this);
        ((TextView) findViewById(R.id.a_t_preparing)).setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent me){
                return mDetector.onTouchEvent(me);
            }
        });
        switch(activityToOpen)
        {
            case "QuickTips":
                prepareForQuickTips();
                break;
            case "Preparing":
                prepareForPreparing();
                break;
            case "Listen":
                prepareForListen();
                break;
            case "Sit":
                prepareForSit();
                break;
            case "Down":
                prepareForDown();
                break;
            case "Stay":
                prepareForStay();
                break;
            case "RollOver":
                prepareForRollOver();
                break;
            case "Fetch":
                prepareForFetch();
                break;
            case "Heel":
                prepareForHeel();
                break;
            default:
                //Should not come here
                break;        }
        //Initially disable the previous button
        //(findViewById(R.id.nav_prev)).setEnabled(false);
        //(findViewById(R.id.nav_prev)).getBackground().setAlpha(64);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String textSize = prefs.getString("font_list", "Medium");
        switch(textSize)
        {
            case "Small":
                ((TextView) findViewById(R.id.a_t_preparing)).setTextAppearance(this,android.R.style.TextAppearance_Small);
                break;
            case "Medium":
                ((TextView) findViewById(R.id.a_t_preparing)).setTextAppearance(this,android.R.style.TextAppearance_Medium);
                break;
            case "Large":
                ((TextView) findViewById(R.id.a_t_preparing)).setTextAppearance(this,android.R.style.TextAppearance_Large);
                break;
            default:
                ((TextView) findViewById(R.id.a_t_preparing)).setTextAppearance(this,android.R.style.TextAppearance_Medium);
                break;
        }
        adsManager = new AdsManager();
        adsManager.createAd(getApplicationContext());

        if(!snackMessageRead)
        {
            Snackbar snackbar = Snackbar
                    .make(((TextView) findViewById(R.id.a_t_preparing)), "Swipe left/right to continue reading", Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            snackMessageRead = true;
                        }
                    });
            snackbar.show();
        }
    }

    private void prepareForQuickTips() {
        ((ImageView) findViewById(R.id.image_preparing)).setImageResource(R.drawable.dog_command_image_quick_tips);
        displayPoints = new String[10];
        currentPage = 1;
        totalPages = 10;
        displayPoints[0] = "LISTEN TO YOUR DOG\n\nOkay, he's finally home. Learn to listen to your dog. If your dog appears to be uncomfortable meeting another dog, animal or person, don’t insist that he say hello. He’s telling you that he isn’t comfortable for a reason, and you should respect that. Forcing the issue can often result in bigger problems down the line.";
        displayPoints[1] = "BE GENEROUS WITH YOUR AFFECTION\n\nMost people don’t have a problem being very clear about when they are unhappy with their dogs, but, they often ignore the good stuff. Big mistake! Make sure you give your dog lots of attention when he’s doing the right thing. Let him know when he’s been a good boy. That’s the time to be extra generous with your attention and praise. It’s even okay to be a little over the top.";
        displayPoints[2] = "DOES HE REALLY LIKE IT?\n\nJust because the bag says “a treat all dogs love” doesn’t mean your dog will automatically love it. Some dogs are very selective about what they like to eat. Soft and chewy treats are usually more exciting for your dog than hard and crunchy treats. Keep your eyes open for what he enjoys.";
        displayPoints[3] = "TELL HIM WHAT YOU WANT HIM TO DO\n\nThere is nothing inherently wrong with telling your dog “no,” except that it doesn’t give him enough information. Instead of telling your dog “no,” tell him what you want him to do. Dogs don’t generalize well, so if your dog jumps up on someone to say hello and you say no, he may jump higher or he may jump to the left side instead of the right. A better alternative would be to ask him to “sit.” Tell him what you want him to do in order to avoid confusion.";
        displayPoints[4] = "BE CONSISTENT\n\nWhenever you’re training your dog, it’s important to get as many family members involved as possible so everyone’s on the same page. If you are telling your dog “off” when he jumps on the couch and someone else is saying “down,” while someone else is letting him hang out up there, how on earth is he ever going to learn what you want? Consistency will be the key to your success.";
        displayPoints[5] = "HAVE REALISTIC EXPECTATIONS\n\nChanging behavior takes time. You need to have realistic expectations about changing your dog’s behavior as well as how long it will take to change behaviors that you don’t like. Often behaviors which are “normal” doggie behaviors will take the most time such as barking, digging and jumping. You also need to consider how long your dog has rehearsed the behavior. For example, if you didn’t mind that your dog jumped up on people to say hi for the last seven years and now you decide that you don’t want him to do that anymore, that behavior will take a much longer time to undo than if you had addressed it when he was a pup. Remember it’s never too late to change the behavior some will just take longer than others.";
        displayPoints[6] = "DON’T UNDERESTIMATE THE BENEFITS OF FEEDING A HIGH QUALITY FOOD\n\nFeed your dog a high-quality diet with appropriate amounts of protein. If your dog spends most of his days lounging in your condo, don’t feed him food with a protein level that is ideal for dogs who herd sheep all day. The money that you will spend on feeding an appropriate quality food will often be money that you save in vet bills later on. I recommend you always check with your veterinarian for the right diet for your dog.";
        displayPoints[7] = "YOU GET WHAT YOU REINFORCE – NOT NECESSARILY WHAT YOU WANT\n\nIf your dog exhibits a behavior you don’t like, there is a strong likelihood that it’s something that has been reinforced before. A great example is when your dog brings you a toy and barks to entice you to throw it. You throw the toy. Your dog has just learned that barking gets you to do what he wants. You say “no,” and he barks even more. Heaven forbid you give in and throw the toy now! Why? Because you will have taught him persistence pays off. Before you know it you’ll have a dog that barks and barks every time he wants something. The solution? Ignore his barking or ask him to do something for you (like “sit”) before you throw his toy.";
        displayPoints[8] = "BRIBERY VS. REWARD\n\nThe idea of using treats to train is often equated with bribery. Truthfully, dogs do what works. If using treats gets them to do what you want, then why not? You can also use the world around you as a reinforcement. Every interaction you have with your dog is a learning opportunity, so when you think about it, you probably don’t use food very often except during active training sessions. So why does your dog continue to hang out? Because you reinforce him with praise, touch, games and walks. Just remember, the behavior should produce the treat; the treat should not produce the behavior.";
        displayPoints[9] = "FREEDOM\n\nLet your new dog gradually earn freedom throughout your home. A common error that many pet parents make is giving their new dog too much freedom too soon. This can easily lead to accidents relating to housetraining and destructive chewing. So, close off doors to unoccupied rooms and use baby gates to section off parts of the house, if necessary. One of the best ways to minimize incidents is to keep your dog tethered to you in the house and by using a crate or doggie safe area when you can’t actively supervise him.";

        heading = "Quick Tips";
        ((TextView) findViewById(R.id.sub_activity_heading)).setText(heading);
        ((TextView) findViewById(R.id.sub_activity_page_number)).setText("Page: "+ currentPage + "/" + totalPages);
        ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[currentPage-1]);
        ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
    }

    private void prepareForPreparing() {
        ((ImageView) findViewById(R.id.image_preparing)).setImageResource(R.drawable.dog_command_image_preparing);
        displayPoints = new String[5];
        currentPage = 1;
        totalPages = 5;
        displayPoints[0] = "Training can be started at any age, the sooner the better. You can start simple training with your puppy as soon as he has settled into his new home. Older dogs are also receptive to training, although some may be less keen or less quick to learn than a younger dog. Done properly, training will be fun, both for you and your dog, as well as exercising his brain and reinforcing the good relationship between you.";
        displayPoints[1] = "In order to be effective and to gain the best results, all training should be based around positive rewards. Positive reward training works because if you reward your dog with something he wants as soon as he does what you ask, he is far more likely to do it again. Rewards can be anything that your dog or puppy really wants and could include; food treats, a favourite toy, playing a certain game or getting a pat. However, really tasty treats will usually work best – try small pieces of dried liver, hotdog sausage, chicken or cheese for maximum effect. If you are using food treats, you will need to reduce the size of your dog's normal meals or use his whole meal divided up into smaller portions, to prevent your dog putting on weight. Always combine the giving of a reward with verbal praise such as \"Good dog\".";
        displayPoints[2] = "When teaching a new command, you will need to reward your dog every time that he does what you ask correctly. Once he has the hang of the command, however, it’s a good idea to change the way you reward by only giving the reward every now and then, because this will make your dog try harder for it. Always verbally praise your dog each time, even if he is not being rewarded with a treat.";
        displayPoints[3] = "Punishment should never be used in training. If you punish your dog, it will only teach him to be scared of you and may eventually teach him to be aggressive. He will mistrust you and your relationship may break down completely.";
        displayPoints[4] = "Avoid punishment in training (and everyday life) by trying the following – it’ll be far more\n" +
                "effective and could improve your relationship with your dog:\n" +
                "\n" +
                "1)\tReward all wanted behaviour – so that your dog is likely to repeat it in the future.\n" +
                "2)\tIgnore unwanted behaviour – so that your dog is less likely to repeat it.\n" +
                "3)\tAvoid triggering the unwanted behaviour – avoidance prevents the unwanted behaviour from happening in the first place!\n" +
                "4)\tWhere unwanted behaviour cannot be ignored or avoided, train an alternative, acceptable behaviour for your dog to perform instead. It is much easier to train a dog to do something else that you don’t mind, than to train him to completely stop something that you do not like. For example, if your dog jumps up at people, teach him a really good ‘sit’ command and ask for this when meeting others. \n" +
                "5)\tIf he is rewarded with treats and attention every time he sits, he’ll soon automatically be doing this when he meets people, instead of jumping up!";

        heading = "Preparing";
        ((TextView) findViewById(R.id.sub_activity_heading)).setText(heading);
        ((TextView) findViewById(R.id.sub_activity_page_number)).setText("Page: " + currentPage + "/" + totalPages);
        ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[currentPage-1]);
        ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
    }

    private void prepareForSit() {
        ((ImageView) findViewById(R.id.image_preparing)).setImageResource(R.drawable.dog_command_image_sit);
        displayPoints = new String[5];
        currentPage = 1;
        totalPages = 5;
        displayPoints[0] = "Does your dog know how to sit on cue? Teaching the \"sit\" command to your dog is usually quite simple, as dogs tend to sit naturally. Sit is an important basic command. It is a way to help your dog settle a little bit in one place and focus on you. It can also help lay the groundwork for the stay command. The key is for your dog to associate the word with the action. The sit command sets the groundwork for other commands like stay and down.";
        displayPoints[1] = "When your dog is in the proper sitting position, his hocks and bottom are firmly planted on the ground. Some dogs will cheat and \"hover\" above the ground a little, so be sure not to reward until that butt is on the ground! Ideally, your dog will remain sitting until you release him (some trainers use the word \"okay\" as a release cue). With practice, you can get your dog to perfect his sit.";
        displayPoints[2] = "Get your dog’s attention and show him that you have a treat in your hand.\n" +
                "Hold the treat just above your dog’s nose (not too high or he might jump).\n" +
                "Say your dog’s name followed by the word “sit,” spoken clearly and firmly.\n" +
                "Move the treat back towards your dog’s ears.\n" +
                "As soon as your dog’s rear lands on the ground, say “good sit” in an upbeat tone.\n" +
                "Give your dog the treat followed by petting and praising.\n";
        displayPoints[3] = "If your dog does not sit on his own after a few tries, avoid pushing him into a sitting position. Dogs don't tend to learn well that way. Also, avoid yelling or punishment. Instead, consider trying more valuable treats, like fresh meat. If you are still having trouble getting your dog to sit with valuable treats, consider marking the behavior. Spend some time watching her. Anytime he naturally sits, praise and reward her, saying the word \"sit.\" Try this every time you see him sitting. You'll want to carry treats with you at all times to make this work well.";
        displayPoints[4] = "Hold short training sessions throughout the day in various locations, both indoor and outdoors. End training session on a positive note (with a success). Train the sit command in various locations. Include the front door and food bowl as regular training locations. This will make him more likely to sit when greeting guests or before feeding. Once your dog becomes an expert at sitting, you won't have to give him a treat each time. However, it's a good idea to give treats occasionally in order to reinforce the behavior. Of course, rewarding with praise is always a good idea.";

        heading = "Sit";
        ((TextView) findViewById(R.id.sub_activity_heading)).setText(heading);
        ((TextView) findViewById(R.id.sub_activity_page_number)).setText("Page: "+ currentPage + "/" + totalPages);
        ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[currentPage-1]);
        ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
    }

    private void prepareForDown() {
        ((ImageView) findViewById(R.id.image_preparing)).setImageResource(R.drawable.dog_command_image_down);
        displayPoints = new String[3];
        currentPage = 1;
        totalPages = 3;
        displayPoints[0] = "Does your dog know how to lie down on cue? Teaching the \"down\" cue to your dog is almost as simple as sit. This is an important basic command. Down can be very useful to help your dog relax in a hectic situation, or to keep him in a stay for a long period of time. It's also  the first step in certain dog tricks, such as roll over. When your dog is in the proper down position, his chest, elbows, and hocks are in contact with the ground. Ideally, your dog will remain down until you release him (many people use the word \"okay\" for the release cue). With practice, you can get your dog to perfect his down.";
        displayPoints[1] = "Get your dog’s attention and show him that you have a treat in your hand. Use your dog’s favorite training treats (ideally, treats should be small and soft).\n" +
                "Hold the treat in front of your dog’s nose.\n" +
                "Slowly move the treat towards the ground.\n" +
                "As soon as your dog’s elbows and hocks are on the ground, give your dog the treat followed by petting and praising.\n" +
                "Once your dog is consistently doing the down motion with the treat, add in the verbal cue. Say the word “down” clearly and firmly while moving the treat to the ground.\n" +
                "Repeat step 5 until your dog lies down with only the verbal cue and no treat-guiding. However, it's best to still give the treat at the end to reward the behavior.";
        displayPoints[2] = "You want to train for 5-10 minutes, 2-3 times per day. Try to end the sessions positively. If needed, find another cue that your dog knows (like sit) and end with that, followed by a treat." +
                "If your dog does not lie down on his own after a few tries, avoid pushing him down into position. Dogs generally do not learn this way. In addition, do not yell at him or punish him. Instead, consider trying more valuable treats, like fresh meat. Try to be patient." +
                "If you are still having trouble getting your dog to lie down with treats, you can try marking the behavior. Next time he naturally lies down, say “down,” then praise and reward him. Try this every time you catch him lying down. You'll probably need to carry treats with you if this is going to work." +
                "Once your dog becomes an expert at lying down, you no longer need to give a treat every time. It's a good idea to give treats occasionally to reinforce the behavior. In addition, rewarding with praise is always a good idea.";

        heading = "Down";
        ((TextView) findViewById(R.id.sub_activity_heading)).setText(heading);
        ((TextView) findViewById(R.id.sub_activity_page_number)).setText("Page: "+ currentPage + "/" + totalPages);
        ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[currentPage-1]);
        ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
    }


    private void prepareForStay() {
        ((ImageView) findViewById(R.id.image_preparing)).setImageResource(R.drawable.dog_command_image_stay);
        displayPoints = new String[4];
        currentPage = 1;
        totalPages = 4;
        displayPoints[0] = "Does your dog know how to stay in place when asked? Almost as important as coming when called, the stay command can prevent your dog from getting involved in dangerous situations. It will also allow you to keep your dog still and calm while you take care of household chores, entertain guests, or bring your dog to public places. A successful “stay” occurs when your dog does not move at all from the original position. Start with 1-2 second periods of staying and work up to several minutes.";
        displayPoints[1] = "Place the collar and long leash on your dog.\n" +
                "Tell him to sit or lie down.\n" +
                "Say “stay” in a firm, clear voice while holding one hand up, palm out (as if to motion stop).\n" +
                "If your dog does not move, give him a treat and praise.\n" +
                "Release your dog from the command by saying “okay” and encouraging him to move.\n" +
                "Instruct your dog to sit again and praise him when he complies.\n" +
                "Say \"stay\" again with the hand motion while taking a step or two back.\n" +
                "If he stays, give him a treat and praise. If he moves, start over from step 1.\n" +
                "Release your dog from the command by saying “okay” and encouraging him to move.\n" +
                "Repeat this process 5-6 times, gradually taking more steps back and increasing the time period between “stay” and “okay”.";
        displayPoints[2] = "Keep training sessions short and try to end on a positive note with a successful action. If your dog cannot yet stay, then end the session with sit or something else your dog knows.\n" +
                "Over time, you should gradually increase the distance between you and your dog. Try to get to the end of the long leash. Remain in your dog’s sight until he understands how to stay. Then, you can try leaving the room after giving the stay command.\n" +
                "Try starting this command in the standing or lying down positions. If successful, your dog should not change positions during the stay command.";
        displayPoints[3] = "Once your dog has mastered the stay command, try practicing with distractions. Get a friend to talk or squeak a toy. Your dog should not move at all despite the distractions.\n" +
                "If you wish to try this outside without a leash, always be sure you are in a fenced-in area.\n" +
                "Once your dog becomes an expert at staying, you no longer need to give a treat every time, only occasionally. However, rewarding with praise is always a good idea.";

        heading = "Stay";
        ((TextView) findViewById(R.id.sub_activity_heading)).setText(heading);
        ((TextView) findViewById(R.id.sub_activity_page_number)).setText("Page: "+ currentPage + "/" + totalPages);
        ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[currentPage-1]);
        ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
    }


    private void prepareForListen() {
        ((ImageView) findViewById(R.id.image_preparing)).setImageResource(R.drawable.dog_command_image_listen);
        displayPoints = new String[2];
        currentPage = 1;
        totalPages = 2;
        displayPoints[0] = "The idea behind name recognition is to teach your dog that his name means something wonderful is about to happen! Start your first session when you have your dog’s attention. With some dog treats on hand, say your dog’s name and then using an indicator (a word like “yes”), immediately give him a treat. Wait a few seconds and then repeat. Do this for five minutes several times a day.\n" +
                "\n" +
                "After a few sessions your pup will probably begin to associate the sound of his name with a tasty treat. Now you should begin the exercise when your dog’s attention is not focused on you. It’s a good idea to have your pet on a Dog Collar and Dog Leash for your training sessions so that you can prevent him from leaving, jumping, or other undesirable behaviors. When your dog is distracted call his name. As soon as he turns and orients toward you, say “yes!” and immediately give him a treat.\n" +
                "\n" +
                "If your dog doesn’t turn to look at you, gently pull him toward you with the leash. When he does finally orient toward you, say “yes!” and give him a dog treat. Repeat this exercise as many times a day as you can and in as many different environments as possible. Turn your daily walks into training walks and before you know it, your dog will be responding to his name reliably.\n";
        displayPoints[1] = "A few words of caution: don’t use your dog’s name too casually otherwise it will become like white noise. Also, never use your dog’s name to correct or punish him in any way. His name should predict something wonderful.\n" +
                "\n" +
                "Ensuring that your dog knows his name is not only a fun exercise – it is a great safety net. Should your dog get away from you for any reason, you want to be able to call your dog’s name and have him return to you. ";
        heading = "Listen";
        ((TextView) findViewById(R.id.sub_activity_heading)).setText(heading);
        ((TextView) findViewById(R.id.sub_activity_page_number)).setText("Page: "+ currentPage + "/" + totalPages);
        ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[currentPage-1]);
        ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
    }
    private void prepareForFetch() {
        ((ImageView) findViewById(R.id.image_preparing)).setImageResource(R.drawable.dog_command_image_fetch);
        displayPoints = new String[6];
        currentPage = 1;
        totalPages = 6;
        displayPoints[0] = "Fetch is a fun game to play with any dog. It involves throwing a ball or other toy and having your dog chase it and bring it back to you. Many people think this game is something dogs do naturally, but very often they need to learn how to do it, or at least some part of it.";
        displayPoints[1] = "Start With Sit\n" +
                "\n" +
                "Before you do anything else, make sure your dog has a good understanding of the sit command. All games of fetch should begin with you asking your dog to sit. Make sure he's sitting calmly next to you before moving on to the next step. This ensures that once he understands the rules of the game, he won't jump up on you to try to get the ball before you even get a chance to throw it.";
        displayPoints[2] = "Send the Dog Out\n" +
                "\n" +
                "Once you get your dog to sit, throw the ball and tell him \"fetch.\" Start off by throwing the ball just a short distance. Most dogs will instinctively chase the ball and pick it up. If so, you're done with this part.\n" +
                "\n" +
                "If fetching doesn't come naturally to your dog, you may have to work on training him to play first." +
                "You can start off by giving him treats or praise for taking any interest in the ball, and slowly work your way up to having him run after and pick up the ball.";
        displayPoints[3] = "Call the Dog Back\n" +
                "\n" +
                "This step and the next are the most important parts of the game of fetch, and the place most people run into trouble. If you can't get your dog to come back and drop the ball, you're not playing fetch, you're playing chase!\n" +
                "\n" +
                "The best way to make your dog return to you with the ball is to make sure he has a strong understanding of the come command before you begin. When playing fetch, as soon as your dog picks up the ball, give the come command. Encourage your dog to come back to you by speaking in a happy voice, patting your legs, and giving him praise.\n" +
                "\n" +
                "If a dog is having trouble with this step, you may need to shorten the distance you throw the ball. In some cases, this may mean to start with just a few feet. Gradually increase the distance you throw the ball. Your dog should be able to consistently bring the ball back to you before you move on to the next distance.";
        displayPoints[4] = "Use a Release Command\n" +
                "\n" +
                "It can be tough to convince a dog to return the ball to you once he has it. It helps if your dog knows the drop it or release command. Practice that before you play fetch with a dog, and as soon as returns to you, give him the command \"drop it.\" If he releases the ball, give him praise, and then throw the ball again as his reward.\n" +
                "\n" +
                "If your dog refuses to release the ball, you need to have a few tricks up your sleeve. Keep some treats on hand. Give your dog the \"drop it\" command, and then show him some treats. He'll have to release the ball in order to get the treats. Make sure you wait until you have the ball back in your possession before giving the dog a treat. Then give him a double reward by throwing the ball to continue the game.\n" +
                "\n" +
                "Another option, instead of treats, is to use two balls. As soon as your dog returns to you with the first ball, show him another ball you're holding in your hand. Many dogs will drop the ball they have in order to go after the second ball. As soon as your dog drops the ball, throw the one in your hand for him to fetch. (Note: This doesn't always work. Some dogs refuse to let go of the ball they already have. In this case, the treat method above would probably work best.)";
        displayPoints[5] = "Never Play Chase\n" +
                "\n" +
                "Keep in mind when you're going through these steps your dog is likely to be just as happy playing chase or keep away as he is playing fetch. Don't get stuck into a game of chase! If your dog runs off with the ball, turn your back to him and begin to walk away. Most dogs will run towards you. If your dog refuses to bring the ball back, end the game.\n" +
                "\n" +
                "For dogs who persist in running away with the ball, try practicing with your dog on a leash. Throw the ball just a short distance, and then give him the come command and then just stand there and wait him out. Use treats and praise to coax him in the right direction.";


        heading = "Fetch";
        ((TextView) findViewById(R.id.sub_activity_heading)).setText(heading);
        ((TextView) findViewById(R.id.sub_activity_page_number)).setText("Page: "+ currentPage + "/" + totalPages);
        ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[currentPage-1]);
        ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
    }

    private void prepareForRollOver() {
        ((ImageView) findViewById(R.id.image_preparing)).setImageResource(R.drawable.dog_command_image_roll_over);
        displayPoints = new String[6];
        currentPage = 1;
        totalPages = 6;
        displayPoints[0] = "\"Roll over\" is a cute and fun trick to teach your dog. Before you start, your dog should already be able to sit and lie down on command. It is a little more difficult to teach your dog to roll over than it is to teach him some other commands, but with a little patience, your dog will be rolling over before you know it.";
        displayPoints[1] = "Start training your dog to roll over by giving him the \"down\" command. Once he is lying down, the next step is to get him to begin to roll. Hold a treat by his nose, and then pull the treat from the tip of his nose toward his shoulder. Your dog should turn his head to follow the treat. If he does, you can continue to pull the treat around his shoulder so he will have to lie down on his side to follow it. Continue holding the treat close to your dog's nose, and pull it all the way around so he'll have to roll all the way over to follow it. If he completes the full roll, praise him or click your clicker and give him a treat.";
        displayPoints[2] = "Break It Down into Smaller Parts\n" +
                "\n" +
                "While it would be great if your dog rolled over all at once, most people find that their dog is not turning all the way around to follow the treat on the first try. Your dog may jump up, wiggle, or move his head around to the other side to try to get the treat. If this is the case with your dog, you can break his training into smaller parts.\n" +
                "\n" +
                "With your dog lying down, hold a treat at your dog's nose and move it towards his shoulder. The moment he turns his head, click or praise him and give him a treat. Practice this several times until he's consistently turning his head.\n" +
                "\n" +
                "Next, stop giving your dog a treat for every head turn. Give treats only for the head turns that bring him closest to lying on his side. Next, only give your dog praise and a treat when he's lying on his side completely. In this way, you can slowly select the behaviors that come closest to rolling over, with each new behavior bringing him closer to completely rolling over. Once you're able to get your dog onto his back, it's fairly simple to lure him over to his other side and into a sitting or standing position by holding the treat in front of his nose.";
        displayPoints[3] = "Troubleshooting\n" +
                "\n" +
                "If your dog is making a lot of mistakes, such as jumping up or turning his head in the opposite direction, you may be moving ahead too quickly. Go back a step or two to when your dog was performing well, and start to slowly build him back up to a full roll over.\n" +
                "\n" +
                "Some dogs can be resistant to lying on their backs and showing their bellies. In this case, make sure your dog knows that training is just fun and games. If he enjoys belly rubs, rub his belly, and click or praise and give him a treat every time he offers you his belly. Be sure to keep your voice light and positive.\n" +
                "\n" +
                "It's also important to keep training sessions short and upbeat. Training sessions that are too long tend to become frustrating for both you and your dog. Keep training for about 10 minutes each time, and try to end each session on a positive note.";
        displayPoints[4] = "Add the Roll Over Command\n" +
                "\n" +
                "When teaching your dog to roll over, it's often easiest to add the command once your dog is consistently rolling all the way over. Once he's smoothly following the treat and rolling over each time, it's time to add the command. Hold the treat in front of him, give the command \"roll over,\" and lure him over with the treat. Practice this over several training sessions.";
        displayPoints[5] = "Stop Using the Treat to Lure Your Dog to Roll Over\n" +
                "\n" +
                "The final step in teaching your dog to roll over is to stop using treats to lure him into the roll. Once your dog has rolled over after hearing the command a number of times, start off by giving the command and waiting a few seconds. Some dogs catch on quickly, and will roll over immediately. Once he has rolled over completely, click or praise and give a treat.\n" +
                "\n" +
                "If your dog doesn't immediately respond to the command, you can phase out the treat more slowly. Start by giving your dog the command \"roll over,\" and use the treat to lure him part of the way over. Move the treat away from him once he is in motion. Slowly decrease how far you lure him with each training session. Most dogs catch on quickly, and will soon be dropping into a roll on your command.";
        heading = "Roll Over";
        ((TextView) findViewById(R.id.sub_activity_heading)).setText(heading);
        ((TextView) findViewById(R.id.sub_activity_page_number)).setText("Page: "+ currentPage + "/" + totalPages);
        ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[currentPage-1]);
        ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
    }

    private void prepareForHeel() {
        ((ImageView) findViewById(R.id.image_preparing)).setImageResource(R.drawable.dog_command_image_heel);
        displayPoints = new String[5];
        currentPage = 1;
        totalPages = 5;
        displayPoints[0] = "Teaching your dog to walk on a loose leash stops him from pulling during walks. It is not a perfect \"heel\" which keeps your dog strictly by your side. It allows your dog room to sniff and explore the neighborhood, as long as he leaves some slack in his leash. In other words, your dog won't be pulling your arm out of its socket as he lunges forward to get to where he wants to go. Instead, he will have to follow your lead in order to be allowed the freedom to see the sights.";
        displayPoints[1] = "Getting Started With Dog Leash Training\n" +
                "\n" +
                "You will need a 6-foot leash and a collar. If your dog is in the habit of pulling, he may be able to easily slip out of a regular flat buckle collar. Martingale collars are another option. These collars are ideal for training a dog to walk on a loose leash. They look like regular flat collars but have an extra loop that pulls tight when your dog pulls to keep him from slipping out. You should also have some treats handy.\n" +
                "\n" +
                "Give the Command\n" +
                "Choose a word or phrase that lets your dog know what is expected of him. Since this is not a formal \"heel,\" something like \"with me\" or \"let's go\" works well. Start out on your walk with your dog at your side, give the command, and begin walking.";
        displayPoints[2] = "Stop and Go\n" +
                "\n" +
                "When your dog pulls at the end of the leash, stop immediately and do not budge. Never allow your dog to move forward when he is pulling or lunging. This way, you are teaching him that the only way for him to get to where he wants to go is by leaving some slack in the leash.\n" +
                "\n" +
                "As soon as there is some slack in the leash, you can begin again. Give your dog the command \"with me\" and start moving forward.";
        displayPoints[3] = "Make It Rewarding\n" +
                "\n" +
                "Once you step out of your house, you have a lot of competition for your dog's attention. You have to make staying close to you more rewarding and fun than running off to explore all the sights and smells of your neighborhood. For this, you can use treats, praise, and a happy tone of voice.\n" +
                "\n" +
                "To start, any time your dog turns and looks at you, tell him \"good boy\" and give him a treat. This is also a good time to use a clicker if you have decided to try clicker training. When your dog's attention turns to you, click and treat. In this way, you are teaching your dog that it is rewarding to pay attention to you. You can also speak to your dog in a high, happy tone to keep his attention on you.\n" +
                "\n" +
                "You may need to use a lot of treats in the beginning to get your dog's attention. Keep your hand at your side and give him treats continuously, as long as he is walking near you with slack in the leash. As he gets the idea of what you expect from him, you can slowly phase out the treats by waiting longer intervals in between giving them out.";
        displayPoints[4] = "Troubleshooting\n" +
                "\n" +
                "There may be times when you just cannot get your dog's attention. He might find what's going on around him more interesting than your treats or happy talk, and stopping and starting may not be enough to distract him from whatever is holding his attention. In this case, you can wait until he lets up a little on the leash, give him the command again, and turn and walk in the opposite direction. Your dog will have no choice but to follow. If he tries to step out in front of you, cut him off and keep walking. Your dog will soon learn to pay attention to you to figure out which way to go.";

        heading = "Heel";
        ((TextView) findViewById(R.id.sub_activity_heading)).setText(heading);
        ((TextView) findViewById(R.id.sub_activity_page_number)).setText("Page: "+ currentPage + "/" + totalPages);
        ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[currentPage-1]);
        ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
    }


   /*public void nextPage(View view) {
        (findViewById(R.id.nav_next)).getBackground().setAlpha(64);
        view.startAnimation(buttonClick);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(16);
        if(currentPage<totalPages) {
            ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[++currentPage-1]);
            ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
            ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
            ((TextView) findViewById(R.id.sub_activity_heading)).setText(heading + " " + currentPage + "/" + totalPages);
            handleNavigationButtons();
        }
    }*/

    /*public void prevPage(View view) {
        view.startAnimation(buttonClick);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(16);
        if(currentPage>1) {
            ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[--currentPage-1]);
            ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
            ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
            ((TextView) findViewById(R.id.sub_activity_heading)).setText(heading + " " + currentPage + "/" + totalPages);
            handleNavigationButtons();
        }
    }*/

    public void handleNavigationButtons()
    {
        if(currentPage==totalPages)
        {
            /*(findViewById(R.id.nav_next)).setEnabled(false);
            (findViewById(R.id.nav_next)).getBackground().setAlpha(64);
            (findViewById(R.id.nav_prev)).setEnabled(true);
            (findViewById(R.id.nav_prev)).getBackground().setAlpha(255);*/

            Intent intent = new Intent(this, AdsActivity.class);
            startActivity(intent);
        }
        else if(currentPage==1)
        {
            /*(findViewById(R.id.nav_prev)).setEnabled(false);
            (findViewById(R.id.nav_prev)).getBackground().setAlpha(64);
            (findViewById(R.id.nav_next)).setEnabled(true);
            (findViewById(R.id.nav_next)).getBackground().setAlpha(255);*/
        }
        else
        {
            /*(findViewById(R.id.nav_prev)).setEnabled(true);
            (findViewById(R.id.nav_prev)).getBackground().setAlpha(255);
            (findViewById(R.id.nav_next)).setEnabled(true);
            (findViewById(R.id.nav_next)).getBackground().setAlpha(255);*/
        }
    }

    public void goToHome(View view) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(16);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /*public boolean onTouch(MotionEvent e){
        //this.mDetector.onTouchEvent(event);
        //return super.onTouchEvent(event);
        return false;
    }*/

    /*@Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        super.dispatchTouchEvent(ev);
        return mDetector.onTouchEvent(ev);
    }*/


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {}

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH){
                return false;
            }
            // right to left swipe
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                onLeftSwipe();
            }
            // left to right swipe
            else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                onRightSwipe();
            }
        } catch (Exception e) {

        }
        return false;
    }

    private void onLeftSwipe() {
        final Context context = this;
        if(currentPage<totalPages) {
            Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
            ((TextView) findViewById(R.id.a_t_preparing)).startAnimation(out);
            out.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(16);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Animation in = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
                    ((TextView) findViewById(R.id.a_t_preparing)).startAnimation(in);
                    in.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[++currentPage - 1]);
                            ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0, 0);
                            ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0, 0);
                            ((TextView) findViewById(R.id.sub_activity_heading)).setText(heading);
                            ((TextView) findViewById(R.id.sub_activity_page_number)).setText("Page: "+ currentPage + "/" + totalPages);
                            handleNavigationButtons();
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
        else
        {
            Toast.makeText(Preparing.this, "Swipe left to move to previous page", Toast.LENGTH_SHORT).show();
        }
    }

    private void onRightSwipe() {
        final Context context = this;
        if(currentPage>1) {
            Animation out = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
            ((TextView) findViewById(R.id.a_t_preparing)).startAnimation(out);
            out.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(16);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Animation in = AnimationUtils.loadAnimation(context,android.R.anim.fade_in);
                    ((TextView) findViewById(R.id.a_t_preparing)).startAnimation(in);
                    in.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[--currentPage-1]);
                            ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
                            ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
                            ((TextView) findViewById(R.id.sub_activity_heading)).setText(heading);
                            ((TextView) findViewById(R.id.sub_activity_page_number)).setText("Page: "+ currentPage + "/" + totalPages);
                            handleNavigationButtons();
                        }
                        @Override
                        public void onAnimationEnd(Animation animation) {}
                        @Override
                        public void onAnimationRepeat(Animation animation) {}
                    });
                }
                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
        }
        else
        {
            Toast.makeText(Preparing.this, "Swipe right to move to next page", Toast.LENGTH_SHORT).show();
        }
    }
}
