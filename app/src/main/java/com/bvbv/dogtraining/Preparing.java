package com.bvbv.dogtraining;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

public class Preparing extends AppCompatActivity {

    String [] displayPoints;
    int currentPage;
    int totalPages;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparing);
        Intent intent = getIntent();
        String activityToOpen = intent.getStringExtra("activityToOpen");
        ((TextView) findViewById(R.id.a_t_preparing)).setMovementMethod(new ScrollingMovementMethod());
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
            default:
                //Should not come here
                break;
        }
        //Initially disable the previous button
        (findViewById(R.id.nav_prev)).setEnabled(false);
        (findViewById(R.id.nav_prev)).getBackground().setAlpha(64);

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
    }


    private void prepareForQuickTips() {
        displayPoints = new String[3];
        currentPage = 1;
        totalPages = 3;
        displayPoints[0] = "Training should be FUN for both of you.\nTraining will exercise his brain.\nTraining positively will build a great relationship between you.\nTraining should be based on POSITIVE rewards.";
        displayPoints[1] = "NEVER punish your dog – this will cause him to be frightened of you.\nSHOW your dog what you are trying to teach him – NEVER physically" +
                " force him.\nBe patient and keep commands consistent.\nDon’t let him get bored, keep training sessions short.";
        displayPoints[2] = "Never ‘train’ in a busy area, only begin to build up the distractions gradually once he knows the commands really well.";
        ((TextView) findViewById(R.id.sub_activity_heading)).setText("Quick Tips");
        ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[currentPage-1]);
    }

    private void prepareForPreparing() {
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

        ((TextView) findViewById(R.id.sub_activity_heading)).setText("Preparing");
        ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[currentPage-1]);
        ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
    }

    private void prepareForSit() {
        displayPoints = new String[2];
        currentPage = 1;
        totalPages = 2;
        displayPoints[0] = "1) Have your dog standing in front of you.\n" +
        "2) Show him that you have a food treat in your hand.\n" +
        "3) Slowly move your hand and treat above and over his head towards his tail, as you give the command 'sit'.\n" +
        "4) His head should go up as he tries to reach the treat, and his bottom should go down into the 'sit' position.\n" +
        "5) Give him the treat and praise him.\n";
        displayPoints[1] = "Do not push his bottom down to make him sit, as he is likely to push up against your hand as " +
                "a result and this may hurt his back.\n\n" +
                "When training your dog to sit, use the command 'sit'. Do not use 'sit down' as this may " +
                "confuse your dog when you try to teach the 'down' command.\n";
        ((TextView) findViewById(R.id.sub_activity_heading)).setText("Sit");
        ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[currentPage-1]);
    }

    private void prepareForDown() {
        displayPoints = new String[2];
        currentPage = 1;
        totalPages = 2;
        displayPoints[0] = "1) Ask your dog to sit and show him the treat in your hand.\n" +
                "2) Slowly move your hand down towards the ground in front of him (just in front of his feet), as you use the command \"down\". He should follow your hand with his nose and lay down.\n" +
                "3) Give him the treat and praise him.";
        displayPoints[1] = "If you have trouble getting him to lie down in this way, put an object such as a coffee table or a " +
                "chair between you and your dog and try again. He will have to lie down to get under the barrier " +
                "to get the treat. Remove the barrier when he gets the hang of it.\n\n" +
                "Do not push or force his back down as he will push against you and this may hurt his back.";
        ((TextView) findViewById(R.id.sub_activity_heading)).setText("Down");
        ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[currentPage-1]);
    }


    private void prepareForStay() {
        displayPoints = new String[2];
        currentPage = 1;
        totalPages = 2;
        displayPoints[0] = "1) Ask him to sit or lay down.\n" +
                "2) Take one step away from him as you command him to \"stay\". Silently count to three.\n" +
                "3) Step back to him, treat and praise.\n" +
                "4) If he gets up, ask him to sit again and repeat the procedure.";
        displayPoints[1] = "Once he is doing this short 'stay' command correctly, gradually increase the distance between you and your dog and/or the time that he is asked to stay.\n" +
                "\nIf he gets up when he is not supposed to, go back a stage to a shorter distance or time, and then increase again slowly until he is doing as he is told every time.";
        ((TextView) findViewById(R.id.sub_activity_heading)).setText("Stay");
        ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[currentPage-1]);
    }


    private void prepareForListen() {
        displayPoints = new String[2];
        currentPage = 1;
        totalPages = 2;
        displayPoints[0] = "Choose a dog that fits your lifestyle. After centuries of breeding, the modern dog is one of the most varied species of animal on earth. While there's probably a dog to suit every lifestyle, not all dogs will fit your specific needs. For example, if you like to relax, you should not get a Jack Russell Terrier, known for its constant barking and high energy. Instead, you might want a bulldog, that would much prefer to cuddle on the couch all day. Research the personalities and care requirements of various breeds. Ask dog owners about their breed's personaity";
        displayPoints[1] = "You are now prepared!";
        ((TextView) findViewById(R.id.sub_activity_heading)).setText("Listen");
        ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[currentPage-1]);
    }

    public void nextPage(View view) {
        (findViewById(R.id.nav_next)).getBackground().setAlpha(64);
        view.startAnimation(buttonClick);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(16);
        if(currentPage<totalPages) {
            ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[++currentPage-1]);
            ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
            handleNavigationButtons();
        }
    }

    public void prevPage(View view) {
        view.startAnimation(buttonClick);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(16);
        if(currentPage>1) {
            ((TextView) findViewById(R.id.a_t_preparing)).setText(displayPoints[--currentPage-1]);
            ((TextView) findViewById(R.id.a_t_preparing)).scrollTo(0,0);
            handleNavigationButtons();
        }
    }

    public void handleNavigationButtons()
    {
        if(currentPage==totalPages)
        {
            (findViewById(R.id.nav_next)).setEnabled(false);
            (findViewById(R.id.nav_next)).getBackground().setAlpha(64);
            (findViewById(R.id.nav_prev)).setEnabled(true);
            (findViewById(R.id.nav_prev)).getBackground().setAlpha(255);
        }
        else if(currentPage==1)
        {
            (findViewById(R.id.nav_prev)).setEnabled(false);
            (findViewById(R.id.nav_prev)).getBackground().setAlpha(64);
            (findViewById(R.id.nav_next)).setEnabled(true);
            (findViewById(R.id.nav_next)).getBackground().setAlpha(255);
        }
        else
        {
            (findViewById(R.id.nav_prev)).setEnabled(true);
            (findViewById(R.id.nav_prev)).getBackground().setAlpha(255);
            (findViewById(R.id.nav_next)).setEnabled(true);
            (findViewById(R.id.nav_next)).getBackground().setAlpha(255);
        }
    }

    public void goToHome(View view) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(16);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
