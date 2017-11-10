package ffscreens.arthylene.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import ffscreens.arthylene.R;
import ffscreens.arthylene.api.DownloadImageRequest;
import ffscreens.arthylene.enumeration.InfoEnum;
import ffscreens.arthylene.objects.Audio;
import ffscreens.arthylene.objects.BeneficeSante;
import ffscreens.arthylene.objects.Caracteristique;
import ffscreens.arthylene.objects.Conseil;
import ffscreens.arthylene.objects.Group;
import ffscreens.arthylene.objects.Marketing;
import ffscreens.arthylene.objects.Photo;
import ffscreens.arthylene.objects.Presentation;
import ffscreens.arthylene.objects.Produit;

import static android.R.drawable.arrow_down_float;
import static android.R.drawable.arrow_up_float;
import static ffscreens.arthylene.R.color.load_color;

public class ProductDetailExpandableListAdapter extends BaseExpandableListAdapter implements DownloadImageRequest.Listener
{
    private Activity activity;
    private Produit produit;
    private Presentation presentation;
    private Photo photo;
    private Caracteristique caracteristique;
    private BeneficeSante beneficeSante;
    private Conseil conseil;
    private Marketing marketing;
    private Audio audio;

    private LayoutInflater inflater;

    private SparseArray<Group> groups;

    private TableRow.LayoutParams layoutParams;

    private ImageView imageViewPicture;
    private Bitmap fruitBitmap;

    //audio part
    private MediaPlayer mediaPlayer = null;
    private Handler mHandler = new Handler();
    private SeekBar seekBarTime;
    private ProgressBar progressBarMusicLoading;
    private TextView textViewCurrentTime, textViewTotalTime;
    private Button btnPlay;


    public ProductDetailExpandableListAdapter(Activity activity, Produit produit, Presentation presentation, Photo photo,
                                              Caracteristique caracteristique, BeneficeSante beneficeSante, Conseil conseil, Marketing marketing, Audio audio)
    {
        this.activity = activity;
        this.produit = produit;
        this.presentation = presentation;
        this.photo = photo;
        this.caracteristique = caracteristique;
        this.beneficeSante = beneficeSante;
        this.conseil = conseil;
        this.marketing = marketing;
        this.audio = audio;

        groups = new SparseArray<Group>();

        for (InfoEnum info : InfoEnum.values())
        {
            Group group = new Group(info.name());
            group.children.add(info.name());
            groups.append(info.ordinal(), group);
        }

        inflater = activity.getLayoutInflater();

        layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(10, 10, 10, 10);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        Group group = (Group) getGroup(groupPosition);

        if(groupPosition == 0)
        {
            convertView = inflater.inflate(R.layout.list_first_item, parent, false);
            convertView.setClickable(true);
            convertView.setFocusable(false);

            imageViewPicture = (ImageView) convertView.findViewById(R.id.imageViewPicture);

            if(fruitBitmap == null)
                new DownloadImageRequest(this).execute(photo.getChemin());
            else
                imageViewPicture.setImageBitmap(fruitBitmap);

            TextView textViewProductName = (TextView) convertView.findViewById(R.id.textViewProductName);

            int idNomProduit = activity.getResources().getIdentifier(produit.getNomProduit().toLowerCase(), "string", activity.getPackageName());

            if(idNomProduit != 0)
                textViewProductName.setText(activity.getString(idNomProduit));
            else
                textViewProductName.setText(produit.getNomProduit());

            textViewProductName.setTextColor(activity.getResources().getColor(load_color));
        }
        else
        {
            convertView = inflater.inflate(R.layout.list_header, parent, false);

            TextView header_text = (TextView) convertView.findViewById(R.id.header);
            String groupeTitle = group.string.substring(0,1).toUpperCase() + group.string.substring(1);

            int idTitle = activity.getResources().getIdentifier(groupeTitle.toLowerCase(), "string", activity.getPackageName());

            if(idTitle != 0)
                header_text.setText(activity.getString(idTitle));
            else
                header_text.setText(groupeTitle);

            header_text.setTextColor(Color.BLACK);

            if (isExpanded)
            {
                header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,arrow_up_float, 0);

            }
            else
            {
                header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, arrow_down_float, 0);

                if(mediaPlayer != null && mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                }
            }
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        convertView = inflater.inflate(R.layout.details_table, parent, false);

        if(groupPosition == InfoEnum.caracteristique.ordinal())
        {
            TextView famille = convertView.findViewById(R.id.textViewTable1);
            TextView contenuFamille = convertView.findViewById(R.id.textViewTable2);
            TextView espece = convertView.findViewById(R.id.textViewTable3);
            TextView contenuEspece = convertView.findViewById(R.id.textViewTable4);
            TextView taillePoids = convertView.findViewById(R.id.textViewTable5);
            TextView contenuTaillePoids = convertView.findViewById(R.id.textViewTable6);
            TextView couleurTexture = convertView.findViewById(R.id.textViewTable7);
            TextView contenuCouleurTexture= convertView.findViewById(R.id.textViewTable8);
            TextView saveur = convertView.findViewById(R.id.textViewTable9);
            TextView contenuSaveur = convertView.findViewById(R.id.textViewTable10);
            TextView principauxProducteur = convertView.findViewById(R.id.textViewTable11);
            TextView contenuPrincipauxProducteur = convertView.findViewById(R.id.textViewTable12);

            famille.setText(R.string.family);
            famille.setTextColor(Color.parseColor("#7D9F00"));
            contenuFamille.setText(caracteristique.getFamille());

            espece.setText(R.string.species);
            espece.setTextColor(Color.parseColor("#7D9F00"));
            contenuEspece.setText(caracteristique.getEspece());

            taillePoids.setText(R.string.size_and_weight);
            taillePoids.setTextColor(Color.parseColor("#7D9F00"));
            contenuTaillePoids.setText(caracteristique.getTaillePoids());

            couleurTexture.setText(R.string.color_and_texture);
            couleurTexture.setTextColor(Color.parseColor("#7D9F00"));
            contenuCouleurTexture.setText(caracteristique.getCouleurTexture());

            saveur.setText(R.string.species);
            saveur.setTextColor(Color.parseColor("#7D9F00"));
            contenuSaveur.setText(caracteristique.getSaveur());

            principauxProducteur.setText(R.string.main_producers);
            principauxProducteur.setTextColor(Color.parseColor("#7D9F00"));
            contenuPrincipauxProducteur.setText(caracteristique.getPrincipauxProducteur());

        }
        else if(groupPosition == InfoEnum.conseil.ordinal())
        {
            TableLayout tableLayout = convertView.findViewById(R.id.tableLayout);

            TextView conseil1 = convertView.findViewById(R.id.textViewTable1);
            TextView conseil2 = convertView.findViewById(R.id.textViewTable2);
            TextView conseil3 = convertView.findViewById(R.id.textViewTable3);
            TextView conseil4 = convertView.findViewById(R.id.textViewTable4);
            TextView conseil5 = convertView.findViewById(R.id.textViewTable5);
            TextView conseil6 = convertView.findViewById(R.id.textViewTable6);


            conseil1.setText(conseil.getConseil1());
            conseil2.setText(conseil.getConseil2());

            conseil3.setText(conseil.getConseil3());
            conseil4.setText(conseil.getConseil4());

            conseil5.setText(conseil.getConseil5());
            conseil6.setText(conseil.getConseil6());

            TableRow tableRow4 = convertView.findViewById(R.id.tableRow4);
            TableRow tableRow5 = convertView.findViewById(R.id.tableRow5);
            TableRow tableRow6 = convertView.findViewById(R.id.tableRow6);

            tableLayout.removeView(tableRow4);
            tableLayout.removeView(tableRow5);
            tableLayout.removeView(tableRow6);
        }
        else if(groupPosition == InfoEnum.beneficeSante.ordinal())
        {
            TableLayout tableLayout = convertView.findViewById(R.id.tableLayout);

            TextView benefice1 = convertView.findViewById(R.id.textViewTable1);
            TextView benefice2 = convertView.findViewById(R.id.textViewTable2);
            TextView benefice3 = convertView.findViewById(R.id.textViewTable3);
            TextView benefice4 = convertView.findViewById(R.id.textViewTable4);
            TextView benefice5 = convertView.findViewById(R.id.textViewTable5);
            TextView benefice6 = convertView.findViewById(R.id.textViewTable6);

            benefice1.setText(beneficeSante.getBenefice1());
            benefice2.setText(beneficeSante.getBenefice2());

            benefice3.setText(beneficeSante.getBenefice3());
            benefice4.setText(beneficeSante.getBenefice4());

            benefice5.setText(beneficeSante.getBenefice5());
            benefice6.setText(beneficeSante.getBenefice6());

            TableRow tableRow4 = convertView.findViewById(R.id.tableRow4);
            TableRow tableRow5 = convertView.findViewById(R.id.tableRow5);
            TableRow tableRow6 = convertView.findViewById(R.id.tableRow6);

            tableLayout.removeView(tableRow4);
            tableLayout.removeView(tableRow5);
            tableLayout.removeView(tableRow6);
        }
        else if(groupPosition == InfoEnum.marketing.ordinal())
        {
            TableLayout tableLayout = convertView.findViewById(R.id.tableLayout);

            TextView marketing1 = convertView.findViewById(R.id.textViewTable1);
            TextView marketing2 = convertView.findViewById(R.id.textViewTable2);

            marketing1.setText(marketing.getMarketing1());
            marketing2.setText(marketing.getMarketing2());

            TableRow tableRow2 = convertView.findViewById(R.id.tableRow2);
            TableRow tableRow3 = convertView.findViewById(R.id.tableRow3);
            TableRow tableRow4 = convertView.findViewById(R.id.tableRow4);
            TableRow tableRow5 = convertView.findViewById(R.id.tableRow5);
            TableRow tableRow6 = convertView.findViewById(R.id.tableRow6);

            tableLayout.removeView(tableRow2);
            tableLayout.removeView(tableRow3);
            tableLayout.removeView(tableRow4);
            tableLayout.removeView(tableRow5);
            tableLayout.removeView(tableRow6);
        }

        else if(groupPosition == InfoEnum.audio.ordinal())
        {
            convertView = inflater.inflate(R.layout.music_player, parent, false);

            if(!audio.getChemin().equals("null"))
            {
                btnPlay = (Button) convertView.findViewById(R.id.btnPlayPause);
                seekBarTime = (SeekBar) convertView.findViewById(R.id.seekBarTime);
                progressBarMusicLoading = (ProgressBar) convertView.findViewById(R.id.progressBarMusicLoading);

                textViewCurrentTime = (TextView) convertView.findViewById(R.id.textViewCurrentTime);
                textViewTotalTime = (TextView) convertView.findViewById(R.id.textViewTotalTime);

                if(mediaPlayer == null)
                {
                    try {
                        Log.i(this.getClass().getName(), "ini media player");
                        mediaPlayer = new MediaPlayer();

                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(audio.getChemin());
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(final MediaPlayer mediaPlayer)
                            {
                                setAudioListener();
                            }
                        });

                        mediaPlayer.prepareAsync();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    setAudioListener();
                }
            }
            else
            {
                TextView textViewNoAudioAvailable = convertView.findViewById(R.id.textViewNoAudioAvailable);
                btnPlay = (Button) convertView.findViewById(R.id.btnPlayPause);
                seekBarTime = (SeekBar) convertView.findViewById(R.id.seekBarTime);
                progressBarMusicLoading = (ProgressBar) convertView.findViewById(R.id.progressBarMusicLoading);

                btnPlay.setVisibility(View.GONE);
                seekBarTime.setVisibility(View.GONE);
                progressBarMusicLoading.setVisibility(View.GONE);

                textViewNoAudioAvailable.setVisibility(View.VISIBLE);

                textViewNoAudioAvailable.setText(activity.getResources().getString(R.string.no_audio));
            }
        }


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        if(groupPosition != 0)
            return false;
        else
            return true;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        if(groupPosition != 0)
            super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        if(groupPosition != 0)
            super.onGroupExpanded(groupPosition);
    }

    @Override
    public void onImageLoaded(Bitmap bitmap)
    {
        fruitBitmap = bitmap;
        imageViewPicture.setImageBitmap(fruitBitmap);
    }

    @Override
    public void onError()
    {
        Log.e(this.getClass().getName(), "Error on loading Image");
    }

    private void setAudioListener()
    {
        seekBarTime.setMax(mediaPlayer.getDuration() /1000);
        progressBarMusicLoading.setVisibility(View.GONE);

        textViewCurrentTime.setVisibility(View.VISIBLE);
        textViewTotalTime.setVisibility(View.VISIBLE);
        textViewTotalTime.setText(" / " + totalTime(mediaPlayer.getDuration()));

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(!mediaPlayer.isPlaying())
                {
                    btnPlay.setText("Pause");
                    mediaPlayer.start();

                    activity.runOnUiThread(new Runnable() {

                        @Override
                        public void run()
                        {
                            int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;

                            seekBarTime.setProgress(mCurrentPosition);
                            textViewCurrentTime.setText(totalTime(mediaPlayer.getCurrentPosition()));

                            mHandler.postDelayed(this, 1000);
                        }
                    });
                }
                else if (mediaPlayer.isPlaying())
                {
                    btnPlay.setText("Play");
                    mediaPlayer.pause();
                }
            }
        });

        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer != null && fromUser){
                    mediaPlayer.seekTo(progress * 1000);
                }
            }
        });
    }

    private String totalTime(int millis)
    {
        if(millis < 0)
        {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        StringBuilder sb = new StringBuilder(64);
        sb.append(minutes);
        sb.append(":");
        sb.append(String.format("%02d", seconds));

        return(sb.toString());
    }
}


